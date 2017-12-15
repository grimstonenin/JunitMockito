package com.orderprocessing;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {


    private OrderDAO orderDAO;



    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public boolean placeOrder(Order order) throws BOException {

        try {
           int result = orderDAO.create(order);
           if(result==0) return false;

        } catch (SQLException e) {
            throw new BOException(e);
        }

        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BOException {
        try {
            Order order = orderDAO.read(id);
            order.setStatus("cancelled");
            int result = orderDAO.update(order);
            if(result==0) return false;
        } catch (SQLException e) {
            throw new BOException(e);
        }

        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BOException {

        try {
            int result = orderDAO.delete(id);
            if(result==0) return false;
        } catch (SQLException e) {
            throw new BOException(e);
        }

        return true;
    }
}
