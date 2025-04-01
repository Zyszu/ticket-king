package pl.matzysz.domain.DTO;

import pl.matzysz.domain.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FlightDTO {

    private long            id;
    private String          aircraftModel;
    private int             availableSeats;
    private Date            departure;
    private String          airfieldFrom;
    private String          airfieldTo;
    private float           pricePerTicket;
    private int             ticketListCount;
    private int             countTicketsLeft;
    private String          carrier;

    public FlightDTO() {}

    public FlightDTO(Flight flight) {
        this.id = flight.getId();
        this.aircraftModel      = flight.getAircraft().getModel();
        this.availableSeats     = flight.getAvailableSeats();
        this.departure          = Date.from(flight.getDeparture().atZone(ZoneId.systemDefault()).toInstant());
        this.airfieldFrom       = flight.getAirfieldFrom();
        this.airfieldTo         = flight.getAirfieldTo();
        this.pricePerTicket     = flight.getPricePerTicket();
        this.ticketListCount    = flight.getTicketList().size();
        this.countTicketsLeft   = this.availableSeats - this.ticketListCount;
        this.carrier            = flight.getAircraft().getCompany().getCompanyName();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getAircraftModel() { return aircraftModel; }
    public void setAircraftModel(String aircraftModel) { this.aircraftModel = aircraftModel; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public Date getDeparture() { return departure; }
    public void setDeparture(Date departure) { this.departure = departure; }

    public String getAirfieldFrom() { return airfieldFrom; }
    public void setAirfieldFrom(String airfieldFrom) { this.airfieldFrom = airfieldFrom; }

    public String getAirfieldTo() { return airfieldTo; }
    public void setAirfieldTo(String airfieldTo) { this.airfieldTo = airfieldTo; }

    public float getPricePerTicket() { return pricePerTicket; }
    public void setPricePerTicket(float pricePerTicket) { this.pricePerTicket = pricePerTicket; }

    public int getTicketListCount() { return ticketListCount; }
    public void setTicketListCount(int ticketListCount) { this.ticketListCount = ticketListCount; }

    public int getCountTicketsLeft() { return countTicketsLeft; }
    public void setCountTicketsLeft(int countTicketsLeft) { this.countTicketsLeft = countTicketsLeft; }

    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }

}
