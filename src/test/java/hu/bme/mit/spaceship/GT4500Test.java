package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mock1;
  private TorpedoStore mock2;

  @BeforeEach
  public void init(){
    this.mock1 = mock(TorpedoStore.class);
    this.mock2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mock1, mock2);

  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(false);
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mock1, times(1)).fire(1);
    verify(mock2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(false);
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert

    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_EmptyFailure(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(true);
    when(mock2.isEmpty()).thenReturn(true);
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mock1, times(0)).fire(1);
    verify(mock2, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_FirstEmptySuccess(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(true);
    when(mock2.isEmpty()).thenReturn(false);
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mock1, times(0)).fire(1);
    verify(mock2, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_MisfireFailure(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(false);
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mock1, times(1)).fire(1);
    verify(mock2, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_EmptyFailure(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(true);
    when(mock2.isEmpty()).thenReturn(true);
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mock1, times(0)).fire(1);
    verify(mock2, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_MisfireFailure(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(false);
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void Constructor_Test(){
    // Arrange
    GT4500 test = new GT4500();

    // Act
    boolean result = test.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void TorpedoStore_Test(){
    // Arrange
    TorpedoStore torp = new TorpedoStore(10);

    // Act
    int numtorp = torp.getTorpedoCount();
    boolean isten = (numtorp == 10);

    // Assert
    assertEquals(true, isten);
  }

  @Test
  public void firelase_Failure(){
    // Arrange

    // Act
    boolean result = ship.fireLaser(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_SingleTwice_SecondEmpty(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(true);
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mock1, times(2)).fire(1);
    verify(mock2, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_SingleTwice_SecondnotEmpty(){
    // Arrange
    when(mock1.isEmpty()).thenReturn(false);
    when(mock2.isEmpty()).thenReturn(false);
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void whenExceptionThrown_thenAssertionSucceeds() {
    // Arrange
    TorpedoStore torp = new TorpedoStore(0);
    boolean test = true;
    try{
    torp.fire(1);
    }
    catch(IllegalArgumentException e){
      assertEquals(true, true);
      test=false;
    }
    if(test)
      assertEquals(true, false);
  }


}
