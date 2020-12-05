from __future__ import division, print_function, unicode_literals
import math
import numpy as np
import matplotlib.pyplot as plt

# Tạo bộ dữ liệu giả, gồm 3 class, mà trong đó không có 2 class nào là có thể phân tách nhau bằng một đường tuyến tính
N=100 # Số lượng điểm dữ liệu trên 1 class
d0= 2 # chiều dữ liệu
C=3 # số lượng class
x=np.zeros((d0, N*C)) # ma trận dữ liệu ( mỗi hàng sẽ là một dữ liệu đơn)
y=np.zeros(N*C, dtype='uint8') # nhãn của các lớp

for j in range(C):
    ix=range(N*j, N*(j+1))
    r=np.linspace(0.0,1,N) # bán kính
    t=np.linspace(j*4, (j+1)*4, N) + np.random.randn(N)*0.2 # theta
    x[:,ix]=np.c_[r*np.sin(t), r*np.cos(t)].T
    y[ix]=j
# trực quan hóa dữ liệu

plt.plot(x[0, :N], x[1, :N], 'bs', markersize=7)
plt.plot(x[0, N:2*N], x[1, N:2*N], 'ro', markersize=7)
plt.plot(x[0, 2*N:], x[1, 2*N:], 'g^', markersize=7)

plt.xlim([-1.5,1.5])
plt.ylim([-1.5,1.5])
cur_axes=plt.gca()
cur_axes.axes.get_xaxis().set_ticks([])
cur_axes.axes.get_yaxis().set_ticks([])

plt.show()

# Một số hàm phụ trợ

def softmax(V):
    e_V=np.exp(V-np.max(V, axis=0, keepdims=True))
    z=e_V/e_V.sum(axis=0)
    return z

# One-hot coding
from scipy import  sparse
def  convert_labels(y,C=3):
    Y=sparse.coo_matrix((np.ones_like(y),(y,np.arange(len(y)))), shape=(C,len(y))).toarray()
    return Y

# cost and loss function

def loss_function(Y, Yhat):
    return -np.sum(Y*np.log(Yhat))/Y.shape[1]

# Phần chương trình chính

d0=2 # dữ liệu 2 chiều
d1=h=100 # kích thước của lớp ẩn
d2=C=3

# Khởi tạo bộ tham số ngẫu nhiên

W1=0.01*np.random.rand(d0,d1)
b1=np.zeros((d1,1))
W2=0.01*np.random.randn(d1,d2)
b2=np.zeros((d2,1))

Y=convert_labels(y,C)
N=x.shape[1]

eta=1 # learning rate
for i in range(10000):
    ## Bước feedforward:
    z1=np.dot(W1.T,x)+b1
    a1=np.maximum(z1,0) # Đây là hàm ReLU, hàm kích hoạt
    z2=np.dot(W2.T,a1)+b2
    Yhat=softmax(z2) # Hàm softmax dùng ở lớp cuối cùng
    loss=loss_function(Y, Yhat) # Y Là nhãn dữ liệu đầu ra, Yhat là dữ liệu tiên đoán
    # in hàm mất mát sau 1000 lần lặp
    if i % 1000==0:
        print("iter %d, loss: %f" %(i, loss))
    # Phần backpropagation ở đây:
    e2=(Yhat-Y)/N
    dw2=np.dot(a1,e2.T)
    db2=np.sum(e2, axis=1, keepdims=True)
    e1=np.dot(W2, e2)
    e1[z1<=0]=0 # Gradient của ReLU
    dw1=np.dot(x, e1.T)
    db1=np.sum(e1,axis=1, keepdims= True)

    # Gradient Descent Update

    W1+=-eta*dw1
    b1+=-eta*db1
    W2+=-eta*dw2
    b2+=-eta*db2
z1=np.dot(W1.T,x)+b1
a1=np.maximum(z1,0)
z2=np.dot(W2.T,a1)+b2
predicted_class=np.argmax(z2, axis=0)
acc=(100*np.mean(predicted_class==y))
print('training accuracy: %.2f %%' % acc)

# Trực quan hóa kết quả

xm = np.arange(-1.5, 1.5, 0.025)
xlen = len(xm)
ym = np.arange(-1.5, 1.5, 0.025)
ylen = len(ym)
xx, yy = np.meshgrid(xm, ym)


# xx, yy = np.meshgrid(np.arange(x_min, x_max, h), np.arange(y_min, y_max, h))
# xx.ravel(), yy.ravel()

print(np.ones((1, xx.size)).shape)
xx1 = xx.ravel().reshape(1, xx.size)
yy1 = yy.ravel().reshape(1, yy.size)

# print(xx.shape, yy.shape)
# XX = np.concatenate((np.ones((1, xx.size)), xx1, yy1), axis = 0)

X0 = np.vstack((xx1, yy1))

# print(X.shape)

z1 = np.dot(W1.T, X0) + b1
a1 = np.maximum(z1, 0)
z2 = np.dot(W2.T, a1) + b2
# predicted class
Z = np.argmax(z2, axis=0)

Z = Z.reshape(xx.shape)
CS = plt.contourf(xx, yy, Z, 200, cmap='jet', alpha = .1)

# Plot also the training points
# plt.scatter(x[:, 1], x[:, 2], c=y, edgecolors='k', cmap=plt.cm.Paired)
# plt.xlabel('Sepal length')
# plt.ylabel('Sepal width')

# X = X.T
N = 100
print(N)


plt.plot(x[0, :N], x[1, :N], 'bs', markersize = 7);
plt.plot(x[0, N:2*N], x[1, N:2*N], 'g^', markersize = 7);
plt.plot(x[0, 2*N:], x[1, 2*N:], 'ro', markersize = 7);
# plt.axis('off')
plt.xlim([-1.5, 1.5])
plt.ylim([-1.5, 1.5])
cur_axes = plt.gca()
cur_axes.axes.get_xaxis().set_ticks([])
cur_axes.axes.get_yaxis().set_ticks([])

plt.xlim(-1.5, 1.5)
plt.ylim(-1.5, 1.5)
plt.xticks(())
plt.yticks(())
plt.title('#hidden units = %d, accuracy = %.2f %%' %(d1, acc))
# plt.axis('equal')
# display(X[1:, :], original_label)
fn = 'ex_res'+ str(d1) + '.png'
# plt.savefig(fn, bbox_inches='tight', dpi = 600)
plt.show()
