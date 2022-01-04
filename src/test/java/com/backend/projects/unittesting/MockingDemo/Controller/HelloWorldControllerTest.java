package com.backend.projects.unittesting.MockingDemo.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorldController.class) // assim em vez de corrermos a app toda, estamos a testar apenas o controller que é aqui definido
class HelloWorldControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void helloWorld_basic() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello-world") // send a GET request to this mapping (correspondente ao do respetivo controller)
                .accept(MediaType.APPLICATION_JSON); // a info que vamos receber com o pedido

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) // para verificar se o status do request é 200
                .andExpect(content().string("Hello World")) // .json(jsoncontnet) o conteudo do json seria aqui em vez duma string hardcoded
                .andReturn();// para chamarmos URIs (que nao sao metodos, nao podemos simplesmente chama los normalmente)

        assertEquals("Hello World", mvcResult.getResponse().getContentAsString()); // compara o resultado final com a frase que é suposto vermos c/ o controller
    }
}