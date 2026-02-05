package com.e3r.project.controllers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

import static com.e3r.project.constants.URLConstants.API_PRICES;
import static com.e3r.project.constants.URLConstants.FIND;
import com.e3r.project.models.dto.price.PriceResponse;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Test Cases Example
    @Test
    void testCase1() throws Exception {
        PriceResponse response = performRequest("2020-06-14-10.00.00", "35455", "1");

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriceList()).isEqualTo(1L);
        assertThat(response.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        assertThat(response.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        assertThat(response.getPriceAmount()).isEqualTo(35.50);
        assertThat(response.getCurrency()).isEqualTo("EUR");
    }

    @Test
    void testCase2() throws Exception {
        PriceResponse response = performRequest("2020-06-14-16.00.00", "35455", "1");

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriceList()).isEqualTo(2L);
        assertThat(response.getPriceAmount()).isEqualTo(25.45);
        assertThat(response.getCurrency()).isEqualTo("EUR");
        assertThat(response.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        assertThat(response.getEndDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 18, 30, 0));
    }

    @Test
    void testCase3() throws Exception {
        PriceResponse response = performRequest("2020-06-14-21.00.00", "35455", "1");

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriceList()).isEqualTo(1L);
        assertThat(response.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        assertThat(response.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        assertThat(response.getPriceAmount()).isEqualTo(35.50);
        assertThat(response.getCurrency()).isEqualTo("EUR");
    }

    @Test
    void testCase4() throws Exception {
        PriceResponse response = performRequest("2020-06-15-10.00.00", "35455", "1");

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriceList()).isEqualTo(3L);
        assertThat(response.getPriceAmount()).isEqualTo(30.50);
        assertThat(response.getCurrency()).isEqualTo("EUR");
        assertThat(response.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        assertThat(response.getEndDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 11, 0, 0));
    }

    @Test
    void testCase5() throws Exception {
        PriceResponse response = performRequest("2020-06-16-21.00.00", "35455", "1");

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriceList()).isEqualTo(4L);
        assertThat(response.getPriceAmount()).isEqualTo(38.95);
        assertThat(response.getCurrency()).isEqualTo("EUR");
        assertThat(response.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        assertThat(response.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
    }

    //Other tests
    @Test
    void limitDateRequestMax() throws Exception {
        PriceResponse response = performRequest("2020-12-31-23.59.59", "35455", "1");

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriceList()).isEqualTo(4L);
        assertThat(response.getPriceAmount()).isEqualTo(38.95);
        assertThat(response.getCurrency()).isEqualTo("EUR");
        assertThat(response.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        assertThat(response.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
    }

    @Test
    void limitDateRequestMin() throws Exception {
        PriceResponse response = performRequest("2020-06-15-16.00.00", "35455", "1");

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1L);
        assertThat(response.getPriceList()).isEqualTo(4L);
        assertThat(response.getPriceAmount()).isEqualTo(38.95);
        assertThat(response.getCurrency()).isEqualTo("EUR");
        assertThat(response.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        assertThat(response.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
    }

    @Test
    void noFoundRequest() throws Exception {

        int status = performBadRequest("2020-06-15-16.00.00", "35455", "2");
        
        assertThat(status).isEqualTo(404); 
    }

    @Test
    void badRequestTest() throws Exception {
        int status = performBadRequest(null, null, null);
        
        assertThat(status).isEqualTo(400); 
    }

    @Test
    void badDateFormatReques() throws Exception {

        int status = performBadRequest("2020-06-15-16:00:00", "35455", "2");
        
        assertThat(status).isEqualTo(400); 
    }

    private PriceResponse performRequest(String date, String productId, String brandId) throws Exception {
        MvcResult result = mockMvc.perform(get(API_PRICES + FIND)
                .param("applicationDate", date)
                .param("productId", productId)
                .param("brandId", brandId))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        return objectMapper.readValue(content, PriceResponse.class);
    }

    private int performBadRequest(String date, String productId, String brandId) throws Exception {
        MvcResult result = mockMvc.perform(get(API_PRICES + FIND)
                .param("applicationDate", date)
                .param("productId", productId)
                .param("brandId", brandId))
                .andReturn();

        return result.getResponse().getStatus();
    }
}