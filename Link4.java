package bigo;

public class Link4 {
	private HuffmanNode data;
    private Link4 next;
    
    public Link4(HuffmanNode item) {
        data = item;
        next = null;
    }
    
    public void setData(HuffmanNode item) {
        data = item;
    }
    
    public void setNext(Link4 next) {
        this.next = next;
    }
    
    public HuffmanNode getData() {
        return data;
    }
    
    public Link4 getNext() {
        return next;
    }
    

}