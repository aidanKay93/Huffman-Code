package bigo;

public class HuffmanPriorityQueue {
    private Link4 head;
    private int count;

    public HuffmanPriorityQueue() {
        head = null;
        count = 0;
    }

    public void insert(HuffmanNode node) {
        Link4 newLink = new Link4(node);

        if (isEmpty() || node.getFrequency() < head.getData().getFrequency()) {
            newLink.setNext(head);
            head = newLink;
        } else {
            Link4 current = head;

            while (current.getNext() != null && node.getFrequency() >= current.getNext().getData().getFrequency()) {
                current = current.getNext();
            }

            newLink.setNext(current.getNext());
            current.setNext(newLink);
        }

        count++;
    }

    public HuffmanNode delete() {
        if (isEmpty()) {
            System.out.println("Queue empty!");
            return null;
        }

        HuffmanNode temp = head.getData();
        head = head.getNext();
        count--;

        return temp;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int getCount() {
        return count;
    }
}