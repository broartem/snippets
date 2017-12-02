# Pure numpy-based snippets for ML
SVM loss and gradient for multi-class classification (see http://cs231n.github.io/linear-classify/#svm):
```python
def svm_loss_vectorized(W, X, y):
  num_classes = W.shape[1]
  num_train = X.shape[0]
  num_dims = W.shape[0]
    
  scores = X.dot(W)
  correct_class_scores = scores[np.arange(num_train), y]
  
  margin = np.maximum(0, scores - np.reshape(correct_class_scores, (num_train, 1)) + 1)
  margin[np.arange(num_train), y] = 0

  indicator = (margin > 0).astype("float32")
  indicator_sums = np.sum(indicator, axis=1)
  indicator[np.arange(num_train), y] = -indicator_sums

  # 3D array of shape (num_train, num_dims, num_classes), containing gradient deltas for
  # each training example `i` in its rows. `X` is broadcasted in `num_classes` dimention and
  # `indicator` is broadcasted in `num_dims` dimention.
  dWi = X.reshape((num_train, num_dims, 1)) * indicator.reshape((num_train, 1, num_classes))

  loss = np.sum(margin) / num_train
  dW = np.sum(dWi, axis=0) / num_train

  return loss, dW
```
Softmax loss and gradient for multi-class classification (see http://cs231n.github.io/linear-classify/#softmax):
```python
def softmax_loss_vectorized(W, X, y):
  num_classes = W.shape[1]
  num_train = X.shape[0]
  num_dims = W.shape[0]
    
  scores = X.dot(W)
  
  # Normalization trick for numerical stability, see http://cs231n.github.io/linear-classify/#softmax
  # "Practical issues: Numeric stability" section 
  scores -= np.max(scores)
  
  correct_class_scores = scores[np.arange(num_train), y]
  
  scores_exp = np.exp(scores)
  scores_exp_sums = np.sum(scores_exp, axis=1)
    
  loss = np.sum(-correct_class_scores + np.log(scores_exp_sums)) / num_train

  # Formula for gradients:
  # ```asciimath
  #   del_(L_i)/del_(W_(cd)) = (-I_{c=y_i}  + e^(f_(ic))/(sum_(j=1)^C e^(f_(ij)))) * X_(id)
  # ```
  # where I_{c=y_i} = 1 only if c=y_i and 0 otherwise (indicator for correct class)
  # and f_(ic) = sum_{d=0}^D X_(id) * W_(jd),
  # i in [0, N) - training example index
  # c = [0, C) - classe index
  # d = [0, D) - index for feature dimention
  margins = scores_exp / np.reshape(scores_exp_sums, (num_train, 1))
  margins[np.arange(num_train), y] -= 1

  # 3D array of shape (num_train, num_dims, num_classes), containing gradient deltas for
  # each training example `i` in its rows. `X` is broadcasted in `num_classes` dimention and
  # `margins` is broadcasted in `num_dims` dimention.
  dWi = X.reshape((num_train, num_dims, 1)) * margins.reshape((num_train, 1, num_classes))
  dW = np.sum(dWi, axis=0) / num_train

  return loss, dW
```
