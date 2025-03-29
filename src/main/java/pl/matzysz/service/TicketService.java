package pl.matzysz.service;

import pl.matzysz.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    public Ticket createTicket(Ticket ticket);
    public List<Ticket> createManyTickets(List<Ticket> tickets);
    public Ticket updateTicket(Ticket ticket);
    public List<Ticket> listTicket();
    public void deleteTicket(long id);
    public Optional<Ticket> getTicket(long id);
}
