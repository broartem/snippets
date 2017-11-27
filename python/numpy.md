# Python numpy snippets
All the tutorials below assume that you have numpy imported as:
```
import numpy as np
```
Get indices for the `k` smallest array elements:
```
idx = np.argpartition(my_array, k)
```
Euclidean distance between each pair of the two collections of inputs, similar to [scipy.spatial.distance.cdist](https://docs.scipy.org/doc/scipy/reference/generated/scipy.spatial.distance.cdist.html#scipy.spatial.distance.cdist) using pure numpy. Actually, we just use formula (a-b)^2 = a^2 - 2*a*b + b^2:
```
np.square(self.X_train).sum(axis=1)[None,:] - 2 * np.sqrt(X.dot(self.X_train.T) + np.square(X).sum(axis=1)[:,None])
```
