import org.example.Math.CalculatorService;
import org.example.Math.MathApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathApplicationTest {
    @Mock
    private CalculatorService calculatorService;
    @InjectMocks
    private MathApplication mathApplication;
    @Test
    public void testAdd(){
        when(calculatorService.add(10,20)).thenReturn(30);

        int expected = 30;
        int actual = mathApplication.add(10,20);
        assertEquals(expected,actual);

        verify(calculatorService).add(10,20);
    }
    @Test
    public void testSubtract(){
        when(calculatorService.subtract(20,10)).thenReturn(10);

        int expected = 10;
        int actual = mathApplication.subtract(20,10);
        assertEquals(expected,actual);
        verify(calculatorService).subtract(20,10);
    }
}
