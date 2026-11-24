# Machine learning

- *Supervised learning*: The model is trained on labeled data, meaning that each training example is paired with an output label. The goal is to learn a mapping from inputs to outputs.
    - *Regression*: The model predicts a continuous output value based on input features.
        - [Linear regression](./linear_regression.ipynb)
    - *Classification*: The model predicts a discrete output label based on input features.
- *Unsupervised learning*: The model is trained on unlabeled data, meaning that the training examples do not have associated output labels. The goal is to find patterns or structure in the data.

## 1. Hypothesis

In supervised learning, a **hypothesis** is the mathematical function your model uses to map input features to a predicted output. You can think of it as an educated guess—parameterized by weights—that improves as the model sees training data.

For **linear regression**, the hypothesis assumes a straight-line (or hyperplane) relationship between input features and the target output.

### Single-Feature Example
Suppose you want to predict house price based on floor area $x$ (in square feet). The hypothesis function is:

$$h_\theta(x) = \theta_0 + \theta_1 x$$

* **$h_\theta(x)$**: The predicted output for input $x$.
* **$\theta_0$ (intercept / bias)**: The baseline prediction when $x = 0$.
* **$\theta_1$ (slope / weight)**: How much the predicted output changes for each one-unit increase in $x$.

### Multi-Feature Generalization
If a house has $n$ features $x_1, x_2, \dots, x_n$ (such as square footage, bedrooms, and age), the hypothesis extends to:

$$h_\theta(\mathbf{x}) = \theta_0 + \theta_1 x_1 + \theta_2 x_2 + \dots + \theta_n x_n = \boldsymbol{\theta}^T \mathbf{x}$$

where $\mathbf{x} = [1, x_1, x_2, \dots, x_n]^T$ and $\boldsymbol{\theta} = [\theta_0, \theta_1, \dots, \theta_n]^T$.

---

## 2. Cost Function

A **cost function** measures how far off the model's predictions are from the true labels across the entire training set. Training a linear regression model means finding parameters $\boldsymbol{\theta}$ that **minimize** this cost.

For linear regression, the standard choice is the **Mean Squared Error (MSE)** cost function (often written with a $\frac{1}{2}$ factor to simplify derivatives):

$$J(\theta_0, \theta_1) = \frac{1}{2m} \sum_{i=1}^{m} \left( h_\theta(x^{(i)}) - y^{(i)} \right)^2$$

* **$m$**: Total number of training examples.
* **$x^{(i)}, y^{(i)}$**: The input feature(s) and actual target label of the $i$-th training example.
* **$h_\theta(x^{(i)}) - y^{(i)}$**: The prediction error (residual) for example $i$.

### Why Square the Error?
1. **Prevents cancellation**: Positive and negative errors do not cancel each other out when summed.
2. **Penalizes larger errors more heavily**: A residual of $4$ contributes $16$ to the squared error, whereas a residual of $2$ contributes $4$.
3. **Smooth, convex shape**: For linear regression with MSE, $J(\theta_0, \theta_1)$ forms a convex bowl-shaped surface with a single global minimum.

---

## 3. Gradient Descent

**Gradient descent** is an iterative optimization algorithm used to find the parameter values that minimize the cost function. Starting from initial values for $\theta_0$ and $\theta_1$, it repeatedly takes steps in the direction of steepest descent—opposite to the gradient of $J$.

### Update Rule
At each step, update all parameters **simultaneously**:

$$\theta_j := \theta_j - \alpha \frac{\partial}{\partial \theta_j} J(\theta_0, \theta_1) \quad \text{for each } j$$

* **$:=$**: Assignment (updating the parameter in place).
* **$\alpha$**: The **learning rate**, a positive scalar controlling step size.

### Partial Derivatives for Linear Regression
Computing the partial derivatives of $J(\theta_0, \theta_1) = \frac{1}{2m} \sum_{i=1}^{m} \left(\theta_0 + \theta_1 x^{(i)} - y^{(i)}\right)^2$ gives the simultaneous update equations:

$$\theta_0 := \theta_0 - \alpha \frac{1}{m} \sum_{i=1}^{m} \left( h_\theta(x^{(i)}) - y^{(i)} \right)$$

$$\theta_1 := \theta_1 - \alpha \frac{1}{m} \sum_{i=1}^{m} \left( h_\theta(x^{(i)}) - y^{(i)} \right) x^{(i)}$$

### Role of the Learning Rate ($\alpha$)
* **Too small**: Convergence is very slow and requires many iterations.
* **Too large**: Steps can overshoot the minimum, causing the cost to oscillate or diverge.
* **Well-chosen**: The cost $J(\theta_0, \theta_1)$ decreases steadily until updates become negligible near the minimum.

---

## Summary Table

| Concept | Role in Linear Regression | Formula |
| :--- | :--- | :--- |
| **Hypothesis** | Predicts target values from input features | $h_\theta(x) = \theta_0 + \theta_1 x$ |
| **Cost Function** | Quantifies overall prediction error across $m$ samples | $J(\theta_0, \theta_1) = \frac{1}{2m} \sum_{i=1}^{m} \left(h_\theta(x^{(i)}) - y^{(i)}\right)^2$ |
| **Gradient Descent** | Iteratively adjusts parameters to minimize cost | $\theta_j := \theta_j - \alpha \frac{\partial J}{\partial \theta_j}$ |