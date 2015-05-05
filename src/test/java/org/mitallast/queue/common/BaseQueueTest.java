package org.mitallast.queue.common;

import org.junit.Before;
import org.mitallast.queue.action.queue.pop.PopRequest;
import org.mitallast.queue.action.queue.pop.PopResponse;
import org.mitallast.queue.action.queue.stats.QueueStatsRequest;
import org.mitallast.queue.action.queue.stats.QueueStatsResponse;
import org.mitallast.queue.action.queues.create.CreateQueueRequest;
import org.mitallast.queue.client.Client;
import org.mitallast.queue.common.settings.ImmutableSettings;
import org.mitallast.queue.node.Node;

public abstract class BaseQueueTest extends BaseIntegrationTest {

    private Node node;
    private String queueName;

    @Before
    public void setUp() throws Exception {
        queueName = randomUUID().toString();
        node = createNode();
    }

    public String queueName() {
        return queueName;
    }

    public Node node() {
        return node;
    }

    public Client localClient() {
        return node.localClient();
    }

    public void createQueue() throws Exception {
        localClient().queues()
            .createQueue(new CreateQueueRequest(queueName(), ImmutableSettings.EMPTY))
            .get();
        assertQueueEmpty();
    }

    public PopResponse pop() throws Exception {
        PopRequest request = new PopRequest();
        request.setQueue(queueName);
        return localClient().queue().popRequest(request).get();
    }

    public void assertQueueEmpty() throws Exception {
        QueueStatsResponse response = localClient().queue()
            .queueStatsRequest(new QueueStatsRequest(queueName()))
            .get();
        assert response.getStats().getSize() == 0 : response.getStats();
    }
}
