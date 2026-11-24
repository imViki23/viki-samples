import numpy as np
import random
import matplotlib.pyplot as plt

def hypothesis(x, w):
    return np.dot(x, w)

def gradient_descent(x, y, lr, iter):
    m = x.shape[0]
    x_b = np.hstack([np.ones((m, 1), dtype=np.float32), x])
    wghts = np.array([0, 0], dtype=np.float32).reshape(-1, 1)
    for _ in range(iter):
        h = hypothesis(x_b, wghts)
        errors = h - y
        gradients = (1 / m) * np.dot(x_b.T, errors)
        wghts = wghts - lr * gradients
    return wghts

m = 50
iter = 250
lr = 0.01

x_train = (np.arange(1, m + 1, dtype=np.float32).reshape(-1, 1) * 100) + np.random.normal(10, 50, size=(m, 1)).astype(np.float32)
x_mean = np.mean(x_train)
x_std = np.std(x_train)
x_norm = (x_train - x_mean) / x_std 

y_train = (np.arange(1, m + 1, dtype=np.float32).reshape(-1, 1) * 1000) + np.random.normal(100, 200, size=(m, 1)).astype(np.float32)
y_mean = np.mean(y_train)
y_std = np.std(y_train)
y_norm = (y_train - y_mean) / y_std

wghts = gradient_descent(x_norm, y_norm, lr, iter)

y_out_norm = hypothesis(np.hstack([np.ones((m, 1), dtype=np.float32), x_norm]), wghts)
y_out = (y_out_norm * y_std) + y_mean

plt.scatter(x_train, y_train)
plt.plot(x_train, y_out)
plt.show()