package pl.matzysz.service;

import pl.matzysz.domain.Ticket;

import java.util.List;

public interface PurchaseService {
    public void startPaymentWindow(List<Long> listTicketId);
    public void confirmPayment(List<Long> listTicketId);
    public void checkPaymentTimeouts();
}
