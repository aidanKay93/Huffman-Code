package bigo;

public class HuffmanTree {
    private HuffmanNode root;

    

    public static HuffmanNode buildHuffmanTree(String message) {
        int[] frequencies = new int[128];

        for (char c : message.toCharArray()) {
            frequencies[c]++;
        }

        HuffmanPriorityQueue priorityQueue = new HuffmanPriorityQueue();

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                HuffmanNode node = new HuffmanNode((char) i, frequencies[i]);
                priorityQueue.insert(node);
            }
        }

        while (priorityQueue.getCount() > 1) {
            HuffmanNode left = priorityQueue.delete();
            HuffmanNode right = priorityQueue.delete();
            HuffmanNode parent = new HuffmanNode(null, left.getFrequency() + right.getFrequency());
            parent.left = left;
            parent.right = right;
            priorityQueue.insert(parent);
        }

        return priorityQueue.delete();
    }

    private void buildTable(HuffmanNode node, String code, String[] codeTable) {
        if (node == null) {
            return;
        }

        if (node.getCharacter() != null) {
            char character = node.getCharacter();
            codeTable[character] = code;
        }

        buildTable(node.getLeft(), code + "0", codeTable);
        buildTable(node.getRight(), code + "1", codeTable);
    }

    public String[] buildHuffmanCodeTable() {
        String[] codeTable = new String[128];
        buildTable(root, "", codeTable);
        return codeTable;
    }

    public String encodeMessage(String message, String[] codeTable) {
        StringBuilder encodedMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            encodedMessage.append(codeTable[c]);
        }

        return encodedMessage.toString();
    }

    public String decodeMessage(String encodedMessage, HuffmanNode root) {
        StringBuilder decodedMessage = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedMessage.toCharArray()) {
            if (bit == '0') {
                current = current.getLeft();
            } else if (bit == '1') {
                current = current.getRight();
            }

            if (current.getLeft() == null && current.getRight() == null) {
                decodedMessage.append(current.getCharacter());
                current = root;
            }
        }

        return decodedMessage.toString();
    }

    public static void main(String[] args) {
        String message = "This is a test: @$&(?~~~~~~~~";

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.root = buildHuffmanTree(message);
        String[] codeTable = huffmanTree.buildHuffmanCodeTable();
        String encodedMessage = huffmanTree.encodeMessage(message, codeTable);
        String decodedMessage = huffmanTree.decodeMessage(encodedMessage, huffmanTree.root);

        System.out.println("Original Message: " + message);
        System.out.println("Encoded Message: " + encodedMessage);
        System.out.println("Decoded Message: " + decodedMessage);
    }
}
