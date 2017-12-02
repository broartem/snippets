# Pure numpy-based snippets for ML
SVM loss and gradient for multi-class classification (see http://cs231n.github.io/linear-classify/#svm):
```
def svm_loss_vectorized(W, X, y, reg):
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
