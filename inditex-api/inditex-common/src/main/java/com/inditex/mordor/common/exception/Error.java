package com.inditex.mordor.common.exception;

public enum Error {

    INTERNAL_SERVER_ERROR("inditex.internal_server_error", "INTERNAL SERVER ERROR"),
    PRODUCT_NOT_FOUND("inditex.product_not_found", "Product not found with parameters ProductId: [%s] ApplicationDate: [%s], BrandId: [%s]"),
    PRODUCT_ID_IS_REQUIRED("inditex.product_id_is_required", "Parameter product_id is required"),
    BRAND_ID_IS_REQUIRED("inditex.brand_id_is_required", "Parameter brand_id is required"),
    APPLICATION_DATE_IS_REQUIRED("inditex.application_date_is_required", "Parameter application_date  is required"),;
    private String code;
    private String name;

    Error(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
