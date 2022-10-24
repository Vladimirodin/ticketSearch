package ru.netology.ticketSearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    Ticket ticket1 = new Ticket(1, 400, "SVO", "ROV", 120);
    Ticket ticket2 = new Ticket(2, 600, "SVO", "GDZ", 180);
    Ticket ticket3 = new Ticket(3, 200, "SVO", "VOG", 100);
    Ticket ticket4 = new Ticket(4, 300, "SVO", "VOG", 100);
    Ticket ticket5 = new Ticket(5, 100, "SVO", "VOG", 100);
    Ticket ticket6 = new Ticket(6, 500, "SVO", "ROV", 120);
    Ticket ticket7 = new Ticket(7, 500, "GDZ", "ROV", 60);

    @Test
    public void whenOnlyTicket() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.findAll("SVO", "GDZ");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeById() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.removeById(6);

        Ticket[] expected = {ticket1};
        Ticket[] actual = manager.findAll("SVO", "ROV");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void severalTicketsFound() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {ticket5, ticket3, ticket4};
        Ticket[] actual = manager.findAll("SVO", "VOG");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void whenNoTicketsFound() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.removeById(7);


        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("GDZ", "ROV");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void inTheOrder() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);

        Ticket[] expected = {ticket1, ticket6};
        Ticket[] actual = manager.findAll("SVO", "ROV");

        Assertions.assertArrayEquals(expected, actual);
    }
}