class Boid {
  
  PVector pos;
  PVector vel;
  PVector acc;
  float size = 2;
  PApplet p;
  
  Boid( PApplet p) {
    this(p, new PVector(random(p.width), random(p.height), random(DEPTH)));
  }
  
  Boid( PApplet p, PVector pos) {
    this(p, pos, new PVector(random(-getMaxVel(BOID_ACCURACY), getMaxVel(BOID_ACCURACY)), random(-getMaxVel(BOID_ACCURACY), getMaxVel(BOID_ACCURACY)), random(-getMaxVel(BOID_ACCURACY), getMaxVel(BOID_ACCURACY))));
  }
  
  Boid( PApplet p, PVector pos, PVector vel) {
    this(p, pos, vel, new PVector(random(-getMaxForce(BOID_ACCURACY), getMaxForce(BOID_ACCURACY)), random(-getMaxForce(BOID_ACCURACY), getMaxForce(BOID_ACCURACY)), random(-getMaxForce(BOID_ACCURACY), getMaxVel(BOID_ACCURACY))));
  }
  
  Boid( PApplet p, PVector pos, PVector vel, PVector acc) {
    this. p   = p  ;
    this. pos = pos;
    this. vel = vel;
    this. acc = acc;
  }
  
  void update (ArrayList <Boid> boids) {
    update(p, boids);
  }
  
  void update (PApplet applet, ArrayList <Boid> boids) {
    checkBorders();
    
    
    acc.mult(0);
    acc. add (calcRandomness().mult(getRandomness(BOID_ACCURACY)));
    acc. add (calcSeperate(boids).mult(getSeperation(BOID_ACCURACY)));
    acc. add (calcAlignment(boids).mult(getAlignment(BOID_ACCURACY)));
    acc. add (calcAttraction(new PVector(applet.mouseX,applet.mouseY, DEPTH/2)).mult(getAttraction(BOID_ACCURACY)));
    acc. add (calcCohesion(boids).mult(getCohesion(BOID_ACCURACY)));
    
    vel. add (acc);
    vel. limit (getMaxVel(BOID_ACCURACY));
    pos. add (vel);
  }
  
  PVector calcCohesion (ArrayList<Boid> boids) {
    
    PVector total = new PVector (0, 0, 0);
    int count = 0;
    
    for (Boid b : boids) {
      if(b == this) continue;
      if(PVector.dist(b.pos, pos) > getAlignmentD(BOID_ACCURACY)) continue;
      total.add(b.pos);
      count++;
    }
    
    if (count > 0) {
      
      total. div ((float) count);
      total. normalize ();
      total. mult (getMaxVel(BOID_ACCURACY));
      total. sub (vel);
      total. limit (getMaxForce(BOID_ACCURACY));
      
    }
    
    return total;
    
  }
  
  PVector calcAlignment (ArrayList <Boid> boids) {
    PVector s = new PVector (0, 0, 0);
    int n = 0;
    
    for (Boid b : boids) {
      if( b.equals (this) ) continue;
      if( b.pos.dist(pos) > BOID_ALIGNMENT_DIST) continue;
      n++;
      s.add(b.vel);
    }
    
    if (n>0) {
      s.div((float)n);
      s.normalize();
      s.mult(getMaxVel(BOID_ACCURACY));
      s.sub(vel);
      s.limit(getMaxForce(BOID_ACCURACY));
    }
    
    return s;
  }
  
  PVector calcSeperate (ArrayList <Boid> boids) {
    float _seperation = 25;
    PVector s = new PVector (0,0,0);
    int n = 0;
    for( Boid b : boids) {
      float d = PVector. dist(pos, b.pos);
      if( d  > 0 && d < _seperation) {
        PVector diff = PVector .sub(pos, b.pos);
        diff.normalize();
        diff.div(d);
        s.add(diff);
        n++;
      }
    }
    if (n>0) {
      s.div(( float )n);
      s.normalize();
      s.mult(getMaxVel(BOID_ACCURACY));
      s.sub(vel);
      s.limit(getMaxForce(BOID_ACCURACY));
    }
    return s;
  }
  
  PVector calcAttraction( PVector spot) {
    
    PVector d = PVector. sub(spot, pos);
    d.normalize();
    d.mult(getMaxVel(BOID_ACCURACY));
    PVector s = PVector. sub(d, vel);
    s.limit(getMaxForce(BOID_ACCURACY));
    
    return s;
    
  }
  
  PVector calcRandomness() {
    
    float x = (float) Math. random();
    float y = (float) Math. random();
    float z = (float) Math. random();
    
    if(Math. random() < 0.5) x = -x;
    if(Math. random() < 0.5) y = -y;
    if(Math. random() < 0.5) z = -z;
    
    PVector r = new PVector (x, y, z);
    
    r.normalize();
    r.mult(getMaxVel(BOID_ACCURACY));
    r.limit(getMaxForce(BOID_ACCURACY));
    
    return r;
    
  }
  
  void checkBorders() {
    if (pos.x < 0       ) pos.x = p.width ;
    if (pos.y < 0       ) pos.y = p.height;
    if (pos.z < 0       ) pos.z = DEPTH   ;
    if (pos.x > p.width ) pos.x = 0       ;
    if (pos.y > p.height) pos.y = 0       ;
    if (pos.z > DEPTH   ) pos.z = 0       ;
  }
  
  void draw () {
    draw (p);
  }
  
  void draw (PApplet applet) {
    applet. fill(175);
    applet. stroke(175);
    applet. pushMatrix();
    applet. translate(pos.x, pos.y, pos.z);
    applet. box(size);
    applet. popMatrix();
    
    if (getDebugDirs()) {
      
      applet.stroke(255);
      applet.line(pos.x, pos.y, pos.z, pos.x + vel.x * BOID_DEBUG_DIRS_MAG, pos.y + vel.y * BOID_DEBUG_DIRS_MAG, pos.z + vel.z * BOID_DEBUG_DIRS_MAG);
      
    }
    
    if (getDebugAlign()) {
      applet.noFill();
      applet.stroke(0,255,0);
      //applet.ellipse(pos.x, pos.y, getAlignmentD(BOID_ACCURACY), getAlignmentD(BOID_ACCURACY));
      applet.pushMatrix();
      applet.translate(pos.x, pos.y, pos.z);
      applet.sphereDetail(3);
      applet.sphere(getAlignmentD(BOID_ACCURACY));
      applet.popMatrix();
    }
  }
  
}