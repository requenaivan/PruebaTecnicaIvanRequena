package com.inditex.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.inditex.zwift.Application;
import constant.Route;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import response.PriceResponseDTO;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private final static String AUTHORIZATION = "Basic aW5kaXRleC1wcmljZTpDYTIzV3ZzMjEy";

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
    public void getPricesByFilters10AM() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-14-10.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_10_00_day_14_brand_1_product_35455.json")));
        ResultActions result= mvc.perform(get(path)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceResponseDTO prices = gson.fromJson(contentResponse, PriceResponseDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters16AM() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-14-16.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_16_00_day_14_brand_1_product_35455.json")));
        ResultActions result= mvc.perform(get(path)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceResponseDTO prices = gson.fromJson(contentResponse, PriceResponseDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters21PM() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-14-21.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_21_00_day_14_brand_1_product_35455.json")));
        ResultActions result= mvc.perform(get(path)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceResponseDTO prices = gson.fromJson(contentResponse, PriceResponseDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters10AMDay15() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-15-10.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_10_00_day_15_brand_1_product_35455.json")));
        ResultActions result= mvc.perform(get(path)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceResponseDTO prices = gson.fromJson(contentResponse, PriceResponseDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    public void getPricesByFilters21PMDay16() throws Exception {
        String path = Route.PRICES;
        Integer brandId = 1;
        String appDate = "2020-06-16-21.00.00";
        Integer productId = 35455;
        String response = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/get_price_21_00_day_16_brand_1_product_35455.json")));
        String responseHandler = IOUtils.toString(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("response/response_handler_not_found_product.json")));
        ResultActions result= mvc.perform(get(path)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
                .queryParam("brand_id", brandId.toString())
                .queryParam("application_date", appDate)
                .queryParam("product_id", productId.toString())
        );
        String contentResponse = result.andReturn().getResponse().getContentAsString();
        PriceResponseDTO prices = gson.fromJson(contentResponse, PriceResponseDTO.class);
        Assertions.assertNotNull(prices);
        result.andExpect(status().is4xxClientError());
        result.andExpect(MockMvcResultMatchers.content().json(response));
    }
}
