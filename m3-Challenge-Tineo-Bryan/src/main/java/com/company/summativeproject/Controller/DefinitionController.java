package com.company.summativeproject.Controller;

import com.company.summativeproject.Model.Definition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class DefinitionController {
    private List<Definition> definitions;
    private static int idCounter = 0;

    public DefinitionController(){
        definitions = new ArrayList<Definition>();
        String[] words = {"Belligerent", "Ameliorate", "Ephemeral", "Serendipity", "Ambivalent", "Conundrum", "Euphoria", "Arduous", "Propensity", "Enigma"};
        String[] meanings = {"exhibiting aggression or hostility towards others", "make something better or improve it", "lasting for a very short time, transitory", "the occurrence of happy events by chance", "having mixed feelings or contradictory ideas about something or someone", "a difficult problem with no easy solution", "a feeling of intense excitement or happiness", "requiring great effort or being hard to do", "a natural tendency or inclination towards a particular characteristic or behavior", "a person or thing that is mysterious or difficult to understand"};
        for (int i = 0; i < words.length; i++) {
            Definition definition = new Definition( idCounter++,words[i], meanings[i]);
            definitions.add(definition);
        }



    }
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    public Definition getRandomWord(){
        int randomIndex = new Random().nextInt(definitions.size());
        return definitions.get(randomIndex);
    }
}