(ns json-zetta-benchmark.core
  (:use zetta.core
        zetta.json
        [clojure.data.json :only (read-json)]
        criterium.core))

(defn reduce-ignore [_ _] 0)

(defn -main [& args]
  (let [complex (slurp "fixtures/complex.json")
        very-complex (slurp "fixtures/very-complex.json")]

  (println "\033[33m=== Bench of json-zetta with parse-once\033[0m")
  (try
    (println "\033[32m== complex input\033[0m")
    (bench (parse-once json complex) :verbose :reduce-with reduce-ignore)
    (catch Exception e (println (.getMessage e))))
  (try
    (println "")
    (println "\033[32m== very complex input\033[0m")
    (bench (parse-once json very-complex) :verbose :reduce-with reduce-ignore)
    (catch Exception e (println (.getMessage e))))

  (println "\033[33m=== Bench of json-zetta with parse\033[0m")
  (try
    (println "\033[32m== complex input\033[0m")
    (bench (parse json complex) :verbose :reduce-with reduce-ignore)
    (catch Exception e (println (.getMessage e))))
  (try
    (println "")
    (println "\033[32m== very complex input\033[0m")
    (bench (parse json very-complex) :verbose :reduce-with reduce-ignore)
    (catch Exception e (println (.getMessage e))))

  (println "\033[33m=== Bench of data.json\033[0m")
  (try
    (println "\033[32m== complex input\033[0m")
    (bench (read-json complex) :verbose :reduce-with reduce-ignore)
    (catch Exception e (println (.getMessage e))))

  (try
    (println "")
    (println "\033[32m== very complex input\033[0m")
    (bench (read-json very-complex) :verbose :reduce-with reduce-ignore)
    (catch Exception e (println (.getMessage e))))))

