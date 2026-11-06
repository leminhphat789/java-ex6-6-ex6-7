package ex66;

import java.util.Objects;

// them phan gioi thieu co dinh 10 phut truoc show
public final class IntroducedShow implements Show {
    public static final int SPEECH_MINUTES = 10;
    private final Show wrapped;
    private final String speaker;
    public IntroducedShow(Show wrapped, String speaker) {
        this.wrapped = Objects.requireNonNull(wrapped);
        this.speaker = Objects.requireNonNull(speaker);
    }
    public Show wrappedShow() { return wrapped; }
    public String description() {
        return "Intro by " + speaker + ": " + wrapped.description();
    }
    public int runningTime() { return SPEECH_MINUTES + wrapped.runningTime(); }
    public Show copy() { return new IntroducedShow(wrapped.copy(), speaker); }
    
}
