package com.company.summativeproject.Controller;

import com.company.summativeproject.Model.Quote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteController {

    private static List<Quote> quoteList;

    public QuoteController() {

        int idCounter = 0;
        Quote quote1 = new Quote(idCounter++, "Emily Dickinson", "That it will never come again is what makes life so sweet.");
        Quote quote2 = new Quote(idCounter++, "George Eliot", "It is never to late to be what you might have been.");
        Quote quote3 = new Quote(idCounter++, "John Steinbeck", "Ideas are like rabbits. You get a couple and learn how to handle them, and pretty soon you have a dozen.");
        Quote quote4 = new Quote(idCounter++, "Kurt Vonnegut", "We have to continually be jumping off cliffs and developing our wings on the way down.");
        Quote quote5 = new Quote(idCounter++, "Jack Kerouac", "One day I will find the right words, and they will be simple.");
        Quote quote6 = new Quote(idCounter++, "Maya Angelou", "I can be changed by what happens to me. But I refuse to be reduced by it.");
        Quote quote7 = new Quote(idCounter++, "Alice Walker", "The most common way people give up their power is by thinking they don't have any.");
        Quote quote8 = new Quote(idCounter++, "Anne Frank", "How wonderful it is that nobody need wait a single moment before starting to improve the world");
        Quote quote9 = new Quote(idCounter++, "Mark Twain", "Focus more on your desire than on your doubt, and the dream will take care of itself.");
        Quote quote10 = new Quote(idCounter++, "Margaret Atwood", "If I waited for perfection, I would never write a word.");

        quoteList = Arrays.asList(quote1, quote2, quote3, quote4, quote5, quote6, quote7, quote8, quote9, quote10);
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public static Quote getQuoteOfTheDay(){

        int randomID = new Random().nextInt(quoteList.size());
        return quoteList.get(randomID);

    }
}
