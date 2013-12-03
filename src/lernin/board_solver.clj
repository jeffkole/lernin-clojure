(ns lernin.board-solver
  (:gen-class))

(def board
  ["acati"
   "headt"
   "dogit"
   "oaref"
   "there"])

(def dictionary
  (sort ["cat" "head" "dog" "he" "here" "are" "there" "it" "dot" "die"]))

(defn ngram
  "Creates a sequence of n-grams from the input, starting with n-grams of
  length n and increasing up to the full length of the input"
  [n data]
  (let [length (count data)
        range-end (+ length 1)]
    (flatten
      (for [partition-length (range n range-end)]
        (for [gram (partition partition-length 1 data)]
          (apply str gram))))))

(defn find-words
  "Given a dictionary, return a sequence of the valid words in the sequence
  of candidate words"
  [dictionary candidates]
  (loop [candidates candidates
         words      '()]
    (if (empty? candidates)
      words
      (let [candidate (first candidates)]
        (recur (rest candidates) (if (some #(= candidate %) dictionary) (cons candidate words) words))))))

(defn transpose
  "Given a 2D sequence, return the columns transposed as rows"
  ;; this works, too: (apply mapv vector matrix)
  ;; though the result is collections of individual characters, instead of
  ;; strings. solved: (map (partial apply str) (apply mapv vector matrix))
  [matrix]
  (for [n (range 0 (count (first matrix)))]
    (apply str (map #(nth % n) matrix))))

(defn -main
  "Determines the valid words in a board made up of a sequence of strings"
  [& args]
  (let [columns    (transpose board)
        candidates (concat board columns)
        words      (distinct (sort (flatten (for [row candidates] (find-words dictionary (ngram 2 row))))))]
    (println (apply str (interpose ", " words)))))
