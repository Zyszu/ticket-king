package pl.matzysz.domain.DTO;

import java.util.List;

public class TicketWrapperDTO {
    private List<Long> ticketIds;

    public List<Long> getTicketIds() { return ticketIds; }
    public void setTicketIds(List<Long> ticketIds) { this.ticketIds = ticketIds; }
}
