package com.rapd.hisabkitab.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppResponsePojo {

    private Object data;
    private String jwt;
    private int    httpResponseCode;
    private String httpResponseBody;

}
