package com.inditex.mordor.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.inditex.mordor.Application;
import com.inditex.mordor.common.constant.ExternalRoute;
import com.inditex.mordor.common.constant.Route;
import com.inditex.mordor.common.response.InditexApiExceptionResponse;
import com.inditex.mordor.common.response.PriceDTO;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@MockServerSettings(ports = {1080}, perTestSuite = true)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class PriceControllerTest {

    @Autowired
    protected MockMvc mvc;
    private MockServerClient mockServerClient;

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
        }
    }).create();

    @Before
    public void initMockServer() {
        mockServerClient = startClientAndServer(1080);
    }
    @After
    public void stopMockServer() {
        mockServerClient.stop();
    }

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters10AMTest() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-14-10.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_10_00_day_14_brand_1_product_35455.json")));
        getPriceMock(HttpStatusCode.OK_200, brandId, productId, appDate,  mockServerClient, response );
        ResultActions result= mvc.perform(get(path)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceDTO prices = gson.fromJson(contentResponse, PriceDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters16AMTest() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-14-16.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_16_00_day_14_brand_1_product_35455.json")));
        getPriceMock(HttpStatusCode.OK_200, brandId, productId, appDate,  mockServerClient, response );
        ResultActions result= mvc.perform(get(path)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceDTO prices = gson.fromJson(contentResponse, PriceDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters21PMTest() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-14-21.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_21_00_day_14_brand_1_product_35455.json")));
        getPriceMock(HttpStatusCode.OK_200, brandId, productId, appDate,  mockServerClient, response );
        ResultActions result= mvc.perform(get(path)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceDTO prices = gson.fromJson(contentResponse, PriceDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters10AMDay15Test() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-15-10.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_10_00_day_15_brand_1_product_35455.json")));
        getPriceMock(HttpStatusCode.OK_200, brandId, productId, appDate,  mockServerClient, response );
        ResultActions result= mvc.perform(get(path)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceDTO prices = gson.fromJson(contentResponse, PriceDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters21PMDay16Test() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-16-21.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_21_00_day_16_brand_1_product_35455.json")));
        getPriceMock(HttpStatusCode.OK_200, brandId, productId, appDate,  mockServerClient, response );
        ResultActions result= mvc.perform(get(path)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceDTO prices = gson.fromJson(contentResponse, PriceDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Product Not found Test")
    public void productNotFoundTest() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2021-06-16-21.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/product_response_null.json")));
        String responseHandler = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/response_handler_not_found_product.json")));
        getPriceMock(HttpStatusCode.OK_200, brandId, productId, appDate,  mockServerClient, response );
        ResultActions result= mvc.perform(get(path)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        InditexApiExceptionResponse prices = gson.fromJson(contentResponse, InditexApiExceptionResponse.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().is4xxClientError());
        result.andExpect(MockMvcResultMatchers.content().json(responseHandler));
    }

    private void getPriceMock(HttpStatusCode httpStatusCode,
                         Integer brandId,
                         Integer productId,
                         String applicationDate,
                         MockServerClient mockServer, String response) {
        String path = ExternalRoute.ENDPOINT_PRICES;

        mockServer.when(
                HttpRequest.request()
                        .withMethod(HttpMethod.GET.name())
                        .withQueryStringParameter("brand_id", brandId.toString())
                        .withQueryStringParameter("product_id", productId.toString())
                        .withQueryStringParameter("application_date", applicationDate.toString())
                        .withPath(path))
                .respond(
                        HttpResponse.response()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withStatusCode(httpStatusCode.code())
                                .withBody(response, StandardCharsets.UTF_8)
                );

    }
}
