

D = 0.5; 
R = 0.01; 
theta = 0; 

sin(asin((D * sin(theta))/R) - theta) * R / sin(theta)

roots([1, -2*D*cos(theta), D^2-R^2])