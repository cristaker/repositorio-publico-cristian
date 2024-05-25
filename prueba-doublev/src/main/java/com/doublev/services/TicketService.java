package com.doublev.services;

import com.doublev.models.Ticket;
import com.doublev.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setFechaActualizacion(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            Ticket existingTicket = ticket.get();
            existingTicket.setUsuario(ticketDetails.getUsuario());
            existingTicket.setFechaActualizacion(LocalDateTime.now());
            existingTicket.setStatus(ticketDetails.getStatus());
            return ticketRepository.save(existingTicket);
        } else {
            throw new RuntimeException("Ticket not found with id " + id);
        }
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
