package code.model.actor.impl;

import code.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class NPCQuestions {
    private static final List<Pair<String, Boolean>> questions = List.of(
        new Pair<>("Is PMO fun?", true),
        new Pair<>("Is SO fun?", true),
        new Pair<>("Is LAG fun?", true),
        new Pair<>("Is PP fun?", true),
        new Pair<>("Is ENS fun?", true),
        new Pair<>("Is SN fun?", true),
        new Pair<>("Is the Runge-Kutta method scalable for each power of 2?", true),
        new Pair<>("Is the Leapfrog method self-starting?", false),
        new Pair<>("Is 'instanceof' a standard java operator?", true),
        new Pair<>("Are there 4 pillars to OO programming?", true),
        new Pair<>("Is Abstraction one of the pillars of OO programming?", true),
        new Pair<>("Is 'holy C' a real programming language?", true),
        new Pair<>("If a banana falls to the ground in a second, how much does the Earth weigh?", false),
        new Pair<>("Will you pass the physics exam this coming exam session?", false),
        new Pair<>("Is functional programming a solid programming paradigm?", true),
        new Pair<>("If you have a series of 20 coin tosses, could you use Bernoulli's model to simplify calculating its expected value?", true),
        new Pair<>("Is Taylor's expansion used for simulating heat dissipation?", true),
        new Pair<>("Is the Gaussian model a discrete probability model?", false)
    );

    private static List<Pair<String,Boolean>> dummyQuestions = new LinkedList<>(questions);


    private static final Random randomNumberRetriever = new Random();
    public static Pair<String, Boolean> getAQuestion(){
        Pair<String, Boolean> res;
        res = dummyQuestions.get(randomNumberRetriever.nextInt(dummyQuestions.size()));
        dummyQuestions.remove(res);
        return res;
    }

    public static void resetQuestions(){
        dummyQuestions = new LinkedList<>(questions);
    }
}
