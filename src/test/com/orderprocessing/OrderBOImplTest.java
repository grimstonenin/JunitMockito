package com.orderprocessing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderBOImplTest {

    @Mock
    private OrderDAO dao;
    private OrderBOImpl orderBO;
    private final int ORDER_ID = 123;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        orderBO = new OrderBOImpl();
        orderBO.setOrderDAO(dao);
    }

    @Test
    public void placeOrderShouldCreateAnOrder() throws SQLException,BOException{

        Order order = new Order();
        when(dao.create(order)).thenReturn(1);

        boolean result = orderBO.placeOrder(order);
        assertTrue(result);
        verify(dao).create(order);
    }

    @Test
    public void placeOrderShouldNotCreateAnOrder() throws SQLException,BOException{

        Order order = new Order();
        when(dao.create(order)).thenReturn(0);

        boolean result = orderBO.placeOrder(order);
        assertFalse(result);
        verify(dao).create(order);
    }

    @Test(expected = BOException.class)
    public void placeOrderShouldThrowBOException() throws SQLException,BOException{

        Order order = new Order();
        when(dao.create(order)).thenThrow(SQLException.class);

        boolean result = orderBO.placeOrder(order);

    }

    @Test
    public void cancelOrderShouldCancelTheOrder() throws SQLException,BOException{

        Order order = new Order();
        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenReturn(1);

        boolean result = orderBO.cancelOrder(ORDER_ID);

        assertTrue(result);
        assertEquals("cancelled",order.getStatus());
        verify(dao).read(ORDER_ID);
        verify(dao).update(order);


    }

    @Test
    public void cancelOrderShouldNotCancelTheOrder() throws SQLException,BOException{

        Order order = new Order();
        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);

        boolean result = orderBO.cancelOrder(ORDER_ID);

        assertFalse(result);
        verify(dao).read(ORDER_ID);
        verify(dao).update(order);


    }

    @Test(expected = BOException.class)
    public void cancelOrderShouldThrowBOExceptionOnRead() throws SQLException,BOException{

        when(dao.read(ORDER_ID)).thenThrow(SQLException.class);

        boolean result = orderBO.cancelOrder(ORDER_ID);

    }

    @Test(expected = BOException.class)
    public void cancelOrderShouldThrowBOExceptionOnUpdate() throws SQLException,BOException{
        Order order = new Order();
        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenThrow(SQLException.class);

        boolean result = orderBO.cancelOrder(ORDER_ID);

    }

    @Test
    public void deleteOrderDeletesTheOrder() throws SQLException,BOException{
        when(dao.delete(ORDER_ID)).thenReturn(1);

        boolean result = orderBO.deleteOrder(ORDER_ID);

        assertTrue(result);
        verify(dao).delete(ORDER_ID);
    }

    @Test(expected = BOException.class)
    public void deleteOrderThrowsBOException() throws SQLException,BOException{
        when(dao.delete(ORDER_ID)).thenThrow(SQLException.class);

        orderBO.deleteOrder(ORDER_ID);
    }
}
