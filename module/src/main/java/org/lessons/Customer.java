package org.lessons;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rent: " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            double thisAnount = 0;
            Rental each = (Rental) rentals.nextElement();

            thisAnount = amountFor(each);

            //Bonus
            frequentRenterPoints++;

            //Bonus for 2 days rent
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && (each.getDaysRented() > 1))
                frequentRenterPoints++;

            //Show rent results
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAnount) + "\n";
            totalAmout += thisAnount;

            //Add a bit frumuseta
            result += "Sum : " + String.valueOf(totalAmout) + '\n';
            result += "You've earned: " + String.valueOf(frequentRenterPoints) + " amount of bonus";
        }
        return result;
    }

    private double amountFor(Rental aRental) {
        double result = 0;

        // Sum for aRental element of string
        switch (aRental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;

                if (aRental.getDaysRented() > 2) result += (aRental.getDaysRented() - 2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                result += (aRental.getDaysRented() * 3);
                break;

            case Movie.CHILDRENS:
                result += 1.5;
                if (aRental.getDaysRented() > 3) {
                    result += (aRental.getDaysRented() - 3) * 1.5;
                    break;
                }
        }
        return result;
    }
}