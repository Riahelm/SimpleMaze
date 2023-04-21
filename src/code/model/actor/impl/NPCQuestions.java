package code.model.actor.impl;

import code.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NPCQuestions {
    private static final List<Pair<String, Boolean>> questions = new LinkedList<>(){{

        add(new Pair<>("Is PMO fun?", true));
        add(new Pair<>("Is SO fun?", true));
        add(new Pair<>("Is LAG fun?", true));
        add(new Pair<>("Is PP fun?", true));
        add(new Pair<>("Is ENS fun?", true));
        add(new Pair<>("Is SN fun?", true));

    }};
    private static final Random randomNumberRetriever = new Random();
    public static Pair<String, Boolean> getAQuestion(){
        Pair<String, Boolean> res;
        res = questions.get(randomNumberRetriever.nextInt(questions.size()));
        questions.remove(res);
        return res;
    }
}
