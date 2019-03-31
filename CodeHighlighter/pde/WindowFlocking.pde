class WindowFlocking extends PApplet {
  
  ArrayList <Boid> boids;
  
  boolean resetNextFrame = false;
  boolean clearNextFrame = false;
  int addNextFrame=0;
  
  public void settings() {
    size (800, 800, P3D);
    boids = new ArrayList <Boid>();
  }
  
  public void addBoid (int num) {
    //for (int i = 0; i < num; i++) boids.add(new Boid( this));
    addNextFrame+=num;
  }
  
  public void delBoid(int num) {
    //for(int i = 0; i < num && boids.size() > 0; i++) boids.remove(0);
    addNextFrame-=num;
  }
  
  public void clearBoids() {
    clearNextFrame = true;
  }
  
  public void resetBoids() {
    resetNextFrame = true;
  }
  
  public void draw() {
    this. background (0);
    setFramerate( this. frameRate);
    for (Boid b : boids) {
      b. update(boids);
      b. draw();
    }
    
    if (getDebugAvgVel()) {
      this. stroke(0,255,0);
      PVector avgVel = getAvgVel().normalize().mult(BOID_DEBUG_AVGVEL_MAG);
      this. line(this. width /2, this. height /2, this. width /2 + avgVel.x, this. height /2 + avgVel.y);
    }
    
    if (getDebugAvgAcc()) {
      this .stroke (255, 0, 0);
      PVector avgAcc = getAvgAcc().normalize().mult(BOID_DEBUG_AVGACC_MAG);
      this .line(this. width /2, this. height /2, this. width /2 + avgAcc.x, this. height /2 + avgAcc.y);
    }
    
    if (resetNextFrame) {
      resetNextFrame = false;
      int num = boids.size();
      boids.clear();
      addBoid(num);
    }
    
    if (clearNextFrame) {
      clearNextFrame = false;
      boids.clear();
    }
    
    if (addNextFrame > 0) {
      addNextFrame--;
      boids.add(new Boid( this));
    }
    
    if (addNextFrame < 0) {
      if (boids.size() == 0) addNextFrame = 0;
      else {
        addNextFrame++;
        boids.remove(0);
      }
    }
  }
  
  public PVector getAvgVel() {
    PVector v = new PVector();
    for( Boid b : boids) v.add(b.vel);
    if( boids.size()==0) return v;
    return v.div(boids.size());
  }
  
  public PVector getAvgAcc() {
    PVector v = new PVector();
    for (Boid b : boids) v.add(b.acc);
    if (boids.size()==0) return v;
    return v.div(boids.size());
  }
  
}