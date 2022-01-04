package com.backend.projects.unittesting.MockingDemo.Business;

import com.backend.projects.unittesting.MockingDemo.Service.SomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SomeBusinessMockTest {

    @InjectMocks
    SomeBusinessImp businessImp;

    @Mock
    SomeDataService someDataServiceMock;

/*  Em vez de criar manualmente uma instância tanto da SomeBusinessImp como do SomeDataService Mock e dps chamar o BeforeEach abaixo,
    podemos fazer tudo automaticamente com o InjectMocks na nossa class e o Mock naquele que criamos criar para injetar
    E depois na class MockTest colocamos a anotaçao ExtendWith e assim ja vai extender e correr os metodos e portanto setar antes dos testes e não precisa do BeforeEach

    SomeBusinessImp businessImp = new SomeBusinessImp();
    SomeDataService someDataServiceMock = mock(SomeDataService.class);


    // Se eu quiser que algo corra antes de cada teste, por exemplo para alguma coisa ser setada, usa-se esta anotação
    // E assim não preciso fazer este set em cada um dos testes porque já vai ser setado para todos antes do teste correr
    @BeforeEach
    public void before() {
        businessImp.setSomeDataService(someDataServiceMock);
    }*/


    @Test
    void calculateSumUsingDataService_basic() {
        when(someDataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});

        // CTRL ALT N para move to inline, em vez de ter variavel expectedResult sozinha numa linha
        assertEquals(6, businessImp.calculateSumUsingDataService());
    }

    @Test
    void calculateSumUsingDataService_empty() {
        when(someDataServiceMock.retrieveAllData()).thenReturn(new int[]{});

        assertEquals(0, businessImp.calculateSumUsingDataService());
    }

    @Test
    void calculateSumUsingDataService_one() {
        when(someDataServiceMock.retrieveAllData()).thenReturn(new int[]{5});

        assertEquals(5, businessImp.calculateSumUsingDataService());
    }

}