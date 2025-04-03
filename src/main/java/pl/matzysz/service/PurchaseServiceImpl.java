package pl.matzysz.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.matzysz.domain.Ticket;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final Map<Long, Instant> ticketStartTime = new ConcurrentHashMap<>();
    private final Map<Long, Boolean> ticketPaid      = new ConcurrentHashMap<>();
    private final TicketService ticketService;

    public PurchaseServiceImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void startPaymentWindow(List<Long> listTicketId) {
        listTicketId.forEach(ticketId -> {
            ticketStartTime.put(ticketId, Instant.now());
            ticketPaid.put(ticketId, false);
        });
    }

    public void confirmPayment(List<Long> listTicketId) {
        listTicketId.forEach(ticketId -> {
            if (!ticketPaid.containsKey(ticketId)) {
                return;
            }
        });

        listTicketId.forEach(ticketId -> {
            ticketPaid.put(ticketId, true);
        });
    }

    // what an ugly chunk of code :D
    @Scheduled(fixedRate = 5000)
    public void checkPaymentTimeouts() {
        ticketStartTime.forEach((ticketId, startTime) -> {
            Instant now = Instant.now();
            if (Duration.between(startTime, now).toSeconds() >= 10 && !ticketPaid.get(ticketId)) {
                ticketPaid.remove(ticketId);
                ticketStartTime.remove(ticketId);
                ticketService.deleteTicket(ticketId);

            } else if (ticketPaid.get(ticketId)) {
                ticketPaid.remove(ticketId);
                ticketStartTime.remove(ticketId);

                ticketService.getTicket(ticketId).ifPresent(ticket -> {
                    ticket.setPaid(true);
                    ticketService.updateTicket(ticket);
                });
            }
        });

    }
}