public class LC331VerifyPreorderSerializationOfABinaryTree {
    public boolean isValidSerialization(String preorder) {
        // when we have one node that is not # then need to add two more nodes that can be find later
        // take one reservedNode when found any node, either # or number
        int reservedNodes = 1;

        for (String s : preorder.split(",")) {
            reservedNodes--;
            if (reservedNodes < 0) {
                return false;
            }
            if (!s.equals("#")) {
                reservedNodes += 2;
            }
        }
        return reservedNodes == 0;
    }
}