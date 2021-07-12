class LC295FindMedianFromDataStream {
    Queue<Integer> min;
    Queue<Integer> max;
    // the whole data can be constructed by max + min, median can be found by checking max.peek and min.peek
    public LC295FindMedianFromDataStream() {
        min = new PriorityQueue<Integer>((a,b) -> {
            return a - b;
        });
        max = new PriorityQueue<Integer>((a, b) -> {
            return b - a;
        });
    }
    
    public void addNum(int num) {
        // when min is empty || max is emtpy
        if (min.isEmpty() && max.isEmpty()) {
            min.offer(num);
        } else if (min.isEmpty()) {
            if (num > max.peek()) {
                min.offer(num);
            } else {
                max.offer(num);
                min.offer(max.poll());
            }
        } else if (max.isEmpty()) {
            if (num < min.peek()) {
                max.offer(num);
            } else {
                min.offer(num);
                max.offer(min.poll());
            }
        } else { // when both min and max have elements
            if (min.size() == max.size()) { // move smaller element to max, greater element to min and always keep the size diff is less than or equals to 1
                if (num > max.peek()) {
                    min.offer(num);
                } else {
                    max.offer(num);
                }
            } else if (min.size() > max.size()) {
                if (num <= min.peek()) {
                    max.offer(num);
                } else {
                    min.offer(num);
                    max.offer(min.poll());
                }
            } else if (min.size() < max.size()) {
                if (num >= max.peek()) {
                    min.offer(num);
                } else {
                    max.offer(num);
                    min.offer(max.poll());
                }
            }
        }
        

    }
    
    public double findMedian() {
        if (min.size() == 0) return (double)max.peek();
        if (max.size() == 0) return (double)min.peek();
        
        if (min.size() == max.size()) {
            return (double)(min.peek() + max.peek()) / 2;
        }
        if (min.size() > max.size()) return (double)min.peek();
        else return (double)max.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
