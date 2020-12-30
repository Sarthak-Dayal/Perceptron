// Activation Function
int sign(float n){
  if(n >= 0) {
    return 1;
  } else {
    return -1;
  }
}

class Perceptron {
  float [] weights = new float[2];
  float learningRate = 0.1;
  // Constructor

  Perceptron() {
    // Initialize weights values
    for(int i = 0; i < weights.length; i++) {
      weights[i] = random(-1,1);
    }
  }

  // Perform prediction
  int predict(float[] inputs) {
    float sum = 0;
    for(int i = 0; i < weights.length; i++){
      sum += inputs[i]*weights[i];
    }

    int output = sign(sum);
    return output;
  }

  void train(float[] inputs, int target) {
    int guess = predict(inputs);
    int err = target - guess;
    console.log(err);
    console.log(weights.length);
    for(int j = 0; j < weights.length; j++) { 
      weights[j] += err * inputs[j] * learningRate;
    }
  }
}

/****************************************************************************/

class Point {
  float x;
  float y;
  int label;

  Point() {
    x = random(width);
    y = random(height);

    if(x > y) {
      label = 1;
    } else {
      label = -1;
    }
  }

  void show() {
    stroke(0);
    if (label == 1){
      fill(255);
    } else {
      fill(0);
    }

    ellipse(x, y, 4, 4);
  }

}

/****************************************************************************/

Perceptron p;

Point[] points = new Point[100];

void setup() {
	size(400, 400);
  p = new Perceptron();

  for(int i = 0; i<points.length; i++){
    points[i] = new Point();
  }

  float[] inputs = {-1.0, 0.5};
  int test = p.predict(inputs);
  println(test);
}

void draw() {

	background(255);
  stroke(0);
  line(0,0,width,height);

  for(Point pt : points) {
    float[] inputs = {pt.x, pt.y};
    int target = pt.label;
    pt.show();
    int g = p.predict(inputs);
    if (g == target) {
      fill(0, 255, 0);
    } else {
      fill(255, 0, 0);
    }
    noStroke();
    ellipse(pt.x, pt.y, 4, 4);
  }
}

void mousePressed() {
  for(Point pt : points) {
    float[] inputs = {pt.x, pt.y};
    int target = pt.label;
    p.train(inputs, target);
  }
}