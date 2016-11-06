package org.mitallast.queue.raft.protocol;

import org.mitallast.queue.common.stream.StreamInput;
import org.mitallast.queue.common.stream.StreamOutput;
import org.mitallast.queue.common.stream.Streamable;

import java.io.IOException;

public class Noop implements Streamable {
    public static final Noop INSTANCE = new Noop();

    public static Noop read(StreamInput stream) throws IOException {
        return INSTANCE;
    }

    private Noop() {
    }

    @Override
    public void writeTo(StreamOutput stream) throws IOException {

    }
}
