# Deep General Purpose MLP Neural Network Primitive

This is a Java-based deep neural network primitive based upon the unit installed in my radio astronomy [pulsar detector](https://github.com/gwyche/nn_pulsar_classifier). With respect to that neural network, this primitive has several advantages. The pulsar detector read data from a MySQL database and input vector dimensions were hard-coded, meaning that input data needed to conform to a fixed dimensionality. This new algorithm reads from a CSV file and is able to autodetect the dimensionality of its datasource, adjusting the size of the input vector dynamically, with the further option to tailor the input vector dimensionality to be greater than the dataset's dimensionality, thus permitting the user to experiment with adjusting the number of input and output vector zeroes to tune training performance. As in the other project, this NN can be scaled to any depth based upon the user's selection. Performance is good, though poorer than the earlier design's, and later versions will incorporate autonomous training management code similar to the kind I wrote in VBA to manage training of a [recurrent neural network](https://gwyche.wordpress.com/2018/05/10/speech-synthesis-with-a-recurrent-neural-net-precursor/). This will act as a countermeasure in the event that numerical instabilities threaten model health. 

After the neural net class is instantiated, a training method within it is called and fed model paramaters and the location of a training data CSV. Training squared error is printed to console in real-time. Once trained, an evaluation subset of the training CSV is automatically used to test model accuracy. The NN's model is then stored in a private attribute. With this out of the way, a seperate method within the NN can be called, now or later on, to study an unclassified specimen by passing that specimen in in the form of an array as an argument. The method will return a 1 dimensional array containing the classification vector. This method will refuse to operate if the model is not trained.

For more information about the general operation of the neural net and the dataset, see this link on my blog: [Pulsar Classification](https://gwyche.wordpress.com/2019/08/06/pulsar-classification/). Note that this blog article was written when I was still feeding the neural network data from a database inside a Spring MVC application.

This neural network is now functional enough and flexible enough for testing on a diverse range of new datasets. I will report on those experiments in a later blog post.


###### Please Note

This is not intended to form the basis for an enterprise application solving hard real-world problems. There are far more optimized and powerful machine learning implementations out there. I build neural net primitives because it's a fun mathematical and technical challenge that improves my mastery of core principles, allows me to be creative, and affords me an opportunity to educate others about ML. That being said, this little guy might wind up being powerful enough for some fun projects. It has been by far my most gory coding challenge to date. I look forward to finding out what it can do.

Checkout my [blog](https://gwyche.wordpress.com) for more of my ML shenanigans.

