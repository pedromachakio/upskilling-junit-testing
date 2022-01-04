package com.backend.projects.unittesting.MockingDemo.Business;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List<String> mockList = mock(List.class);

    @Test
    public void size_basic() {
        when(mockList.size()).thenReturn(5); // quando o size for chamado, quero que retorne 5
        assertEquals(5, mockList.size()); // definimos o expected, 5, e chamo o size (que vai retornar 5 graças à linha anterior)
    }

    @Test
    public void returnDifferentValues() {
        when(mockList.size()).thenReturn(5).thenReturn(10); // quando o size for chamado, quero que retorne 5 e na segunda vez que for chamado quero 10
        assertEquals(5, mockList.size()); // definimos o expected, 5, e chamo o size (que vai retornar 5 graças à linha anterior)
        assertEquals(10, mockList.size());
    }

    @Test
    public void returnWithParameters() {
        when(mockList.get(0)).thenReturn("No index 0 tou eu pa"); // quando o user chamar o get da list na posição 0 vai retornar esta string
        assertEquals("No index 0 tou eu pa", mockList.get(0)); // digo o que é esperado receber e chamo o método que quero testar
    }

    @Test
    public void returnWithGenericParameters() {
        when(mockList.get(anyInt())).thenReturn("No index 0 tou eu pa"); // em qualquer index (provided pelo anyInt - ps existe any para "todos" os tipos)
        assertEquals("No index 0 tou eu pa", mockList.get(0));
        assertEquals("No index 0 tou eu pa", mockList.get(69));
    }

    @Test
    public void verificationBasics() {
        // Faz de conta que isto está a ser feito no sítio onde estou a testar SUT system under test
        String value = mockList.get(0);
        String elValue = mockList.get(1);
        String elValuee = mockList.get(1);

        // How do we verify if the method get was actually called on the mock?
        verify(mockList).get(0); // verify that the method was called 1 time with X specific argument or using ArgumentMatchers como na linha 36

        verify(mockList, times(2)).get(1); // verificar que o método foi chamado *times* amount of times
        verify(mockList, atLeast(1)).get(1); // verificar que o método foi chamado PELO MENOS *times* amount of times
        verify(mockList, atMost(2)).get(1); // verificar que o método foi chamado NO MAXIMO *times* amount of times
        verify(mockList, never()).get(3); // verificar que o método NUNCA é chamado
    }

    // como capturar o argumento que é passado numa method call
    @Test
    public void argumentCapturing() {
        // Faz de conta que isto está a ser feito no sítio onde estou a testar, ou seja no SUT system under test
        mockList.add("SomeString"); // e queríamos capturar esta string que estava aqui a ser adicionada

        //verification with the argument capturer
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class); // String.class porque é o tipo do que arg que queremos capturar
        verify(mockList).add(argumentCaptor.capture());

        assertEquals("SomeString", argumentCaptor.getValue());

    }

    // como capturar argumentos em várias method calls

    @Test
    public void multipleArgumentCapturing() {
        //SUT
        mockList.add("SomeString1");
        mockList.add("SomeString2");

        // verification
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList, times(2)).add(argumentCaptor.capture()); // nao esquecer que o times implicitamente é igual a 1 por isso tenho que
        // declarar explicitamente que vai ser mais de uma vez

        List<String> allValues = argumentCaptor.getAllValues();

        assertEquals("SomeString1", allValues.get(0));
        assertEquals("SomeString2", allValues.get(1));
    }

    public void spying() {

        ArrayList arrayListMock = mock (ArrayList.class);


        arrayListMock.get(0); // null
        arrayListMock.size(); // 0
        arrayListMock.add("Blabla");
        arrayListMock.size(); // vai continuar a ser 0 mesmo depois da linha anterior porque os mocks não retêm comportamento, para isso see below

        when(arrayListMock.size()).thenReturn(3); // aqui digo que quando o .size for chamado quero que o valor retornado seja 3 (ou any other value)
        arrayListMock.size(); // aqui ja vai dar 3, graças à linha anterior

    }
}
