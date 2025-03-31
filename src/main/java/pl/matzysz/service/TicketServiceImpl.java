package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Ticket;
import pl.matzysz.domain.User;
import pl.matzysz.repository.TicketRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(
            TicketRepository ticketRepository
    ) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public List<Ticket> createManyTickets(List<Ticket> tickets) {
        return ticketRepository.saveAll(tickets);
    }

    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public List<Ticket> listTicket() {
        return ticketRepository.findAll();
    }

    @Transactional
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    @Transactional
    public Optional<Ticket> getTicket(long id) {
        return ticketRepository.findById(id);
    }

    @Transactional
    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findAllTicketsByUserWithDetails(user.getId());
    }

}
