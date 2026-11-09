# Understanding Differentiation: Slope, Tangent Lines, and Small Deltas

In calculus, differentiation is all about finding the **rate of change** or the **slope** of a function at any exact point. 

While straight lines have a constant slope ($y = mx + b$), curves change their slope continuously. To find the slope of a curve at a specific point, we look at a tiny shift.

---

## The Andrew Ng Approach: Numerical Estimation

Imagine you have a non-linear function, such as:
$$f(x) = 3x^2$$

You want to find the exact slope of this curve when **$x = 2$**. 

Instead of using complex limit formulas right away, we can approximate the slope by taking a tiny step away from $2$—let's call that tiny step **$\epsilon$ (epsilon)** or **$\Delta x$**.

### Step 1: Pick Two Very Close Values of $x$
Let's choose a tiny value for our step, say $\epsilon = 0.0001$.

1. **First point ($x$):** $2$
   * Calculate $f(2)$: 
     $$3(2)^2 = 3(4) = 12$$
   * So, our first coordinate is $(2,\ 12)$.

2. **Second point ($x + \epsilon$):** $2 + 0.0001 = 2.0001$
   * Calculate $f(2.0001)$: 
     $$3(2.0001)^2 = 3(4.00040001) \approx 12.00120003$$
   * So, our second coordinate is $(2.0001,\ 12.00120003)$.

### Step 2: Calculate the Slope ($\text{Rise over Run}$)
The standard formula for the slope ($m$) between two points is:
$$m = \frac{\text{Change in } y}{\text{Change in } x} = \frac{f(x + \epsilon) - f(x)}{\epsilon}$$

Plug our numbers in:
$$m = \frac{12.00120003 - 12}{0.0001}$$

$$m = \frac{0.00120003}{0.0001} \approx \mathbf{12.0}$$

By making $\epsilon$ infinitesimally small, the numerical approximation settles right on **$12$**. This means the slope of the curve $3x^2$ at the point $x = 2$ is exactly $12$.

---

## Moving from Numerical to Analytical (The Power Rule)

Doing this calculation with tiny numbers for every single point on a curve would take forever. That is why calculus developed general algebraic rules (like the **Power Rule**).

For any function of the form:
$$f(x) = ax^n$$

The derivative (denoted as $\frac{df}{dx}$ or $f'(x)$) is found using the rule:
$$\frac{d}{dx}(ax^n) = a \cdot n \cdot x^{n-1}$$

Let's test this rule on Andrew Ng's example, $f(x) = 3x^2$:
1. Bring the exponent ($2$) down to multiply the coefficient: $3 \times 2 = 6$.
2. Subtract $1$ from the exponent: $2 - 1 = 1$.
3. Result: **$6x$**

Now, plug in our point $x = 2$:
$$6(2) = \mathbf{12}$$

It gives us the exact same result ($12$), but instantly! In machine learning (like gradient descent), knowing how to find this slope allows algorithms to figure out which direction to adjust weights to minimize error.

---
