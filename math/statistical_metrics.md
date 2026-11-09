# Statistical Metrics: Mean, Median, and Standard Deviation

Understanding the differences between **mean**, **median**, and **standard deviation** is fundamental to data analysis. The first two measure **central tendency** (where the middle of your data is), while the third measures **dispersion** (how spread out or varied the data is).

---

## 1. Mean (The Average)
* **What it is:** The sum of all values in a dataset divided by the total number of values.
* **Formula:** 
  * Population Mean: $\mu = \frac{\sum x}{N}$
  * Sample Mean: $\bar{x} = \frac{\sum x}{n}$
* **Best used when:** Your data is distributed relatively symmetrically without extreme outliers (e.g., test scores, heights).
* **The Catch:** It is heavily influenced by **outliers** (extreme high or low values). For example, if 9 people in a room earn \$50,000/year and a billionaire walks in, the *mean* income skyrockets, even though it doesn't represent anyone in the room accurately.

---

## 2. Median (The Middle Value)
* **What it is:** The exact middle number when all data points are sorted in ascending or descending order. If there is an even number of data points, it is the average of the two middle numbers.
* **Formula / Position:** Position of median = $\frac{n + 1}{2}$-th term (for sorted odd/even datasets).
* **Best used when:** Your data is skewed or contains outliers (e.g., housing prices, household incomes), because extreme values don't drag the median around.
* **The Catch:** It ignores the actual magnitude of values outside the middle. Moving a few numbers around at the extremes won't change the median at all, even if the total sum changes dramatically.

---

## 3. Standard Deviation (The Spread)
* **What it is:** A measure of how much, on average, individual data points deviate (are spread out) from the mean.
* **Formula:**
  * Population Standard Deviation: $\sigma = \sqrt{\frac{\sum (x - \mu)^2}{N}}$
  * Sample Standard Deviation: $s = \sqrt{\frac{\sum (x - \bar{x})^2}{n - 1}}$
* **Key Interpretations:**
  * **Low standard deviation:** Data points are tightly clustered close to the mean (consistent, low variability).
  * **High standard deviation:** Data points are spread out over a wide range of values (high variability).
* **Best used when:** You need to understand consistency, risk, or reliability alongside the average. 

---

## Quick Comparison Table

| Feature | Mean | Median | Standard Deviation |
| :--- | :--- | :--- | :--- |
| **Statistical Type** | Central Tendency | Central Tendency | Dispersion / Spread |
| **Affected by Outliers?** | **Yes** (very sensitive) | **No** (resistant) | **Yes** (squares distances, making outliers heavily weighted) |
| **Best For** | Symmetrical, normal distributions | Skewed data with outliers | Measuring consistency or risk |

---

## Real-World Use Case: Monitoring API Latency

Imagine you lead a team managing a backend microservice. You pull a random sample of response times (in milliseconds) for a critical checkout endpoint over the last hour:

$$\text{Latencies (ms): } [110,\ 115,\ 120,\ 125,\ 130,\ 140,\ 150,\ 180,\ 210,\ 1200]$$

*(Note that the final request took 1,200 ms—a massive spike caused by a sudden database connection timeout).*

### 1. The Mean (The Average)
* **Calculation:** Add all values up ($2,400\text{ ms}$) and divide by the total number of requests ($10$).
* **Result:** $\mathbf{240\text{ ms}}$
* **Insight:** Gives you a baseline of overall resource consumption, but can be severely skewed by a single bad outlier.

### 2. The Median (The Typical Experience)
* **Calculation:** Sort the numbers and pick the middle. Since there are 10 numbers, it is the average of the 5th and 6th values ($130$ and $140$).
* **Result:** $\mathbf{135\text{ ms}}$
* **Insight:** Tells you what a **typical user** actually experiences, effectively ignoring extreme network glitches or performance spikes.

### 3. The Standard Deviation (The Consistency / Stability)
* **Calculation:** Measures how far away, on average, each data point is from the mean ($240\text{ ms}$).
* **Result:** $\approx \mathbf{312\text{ ms}}$
* **Insight:** A standard deviation larger than the mean itself indicates that your system's performance is **wildly inconsistent** and unpredictable.