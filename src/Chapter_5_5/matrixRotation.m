

%% collision orientation 
A1 = [2;   2]; % attacking vector 
gamma = 0/180*pi; % plane angle 

R = @(x) [cos(x) -sin(x); sin(x) cos(x)]; 
R_mirr = [-1 0; 0 1];

%A2 = R(gamma) * R_mirr * R(-gamma) * A1; 
M = @(T)[ -cos(2*T), -sin(2*T); -sin(2*T),  cos(2*T)]; 
A2 = M(gamma) * A1; 


plane =  R(gamma) * [1; 0]; 


figure(1)
hold on;
plot([0 A1(1)],[0 A1(2)],'k'); % show A1
plot([0 A2(1)],[0 A2(2)],'b--'); % show A2

plot([0 plane(1)*10],[0 plane(2)*10],'r--'); % show A2
plot([0 plane(1)*-10],[0 plane(2)*-10],'r--'); % show A2

hold off; 

xlim([-5, 5]);
ylim([-5, 5]);


%% solving system analytically: 

syms m1 m2 v1x v1y v2x v2y mom_x0 mom_y0 E0 alpha beta

% conservation of momentum 
% mom_x0 = m1*v1x + m2*v2x; -> v1x = (mom_x0 - m2*v2x)/m1
% mom_y0 = m1*v1y + m2*v2y; -> v1y = (mom_y0 - m2*v2y)/m1; 

% conservation of energy 
% E0 = 1/2*m1*(v1x^2 + v1y^2) + 1/2*m2*(v2x^2 + v2y^2);  (replace v1x and v1y from previous eqs)                                 <- 4 unknown  
% this results in:  
% E0 = 1/2*m1*(((mom_x0 - m2*v2x)/m1)^2 + ((mom_y0 - m2*v2y)/m1)^2) + 1/2*m2*(v2x^2 + v2y^2);                                    <- 2 unknowns 

% conservation of kinetic symmetry
% M*[v2x; v2y] = [alpha, beta]
% (where M = [ -cos(2*T), -sin(2*T); -sin(2*T),  cos(2*T)]; 
%
% if (abs(alpha) > abs(beta)) -> v2y = (beta/alpha)*v2x 
% E0 = 1/2*m1*(((mom_x0 - m2*v2x)/m1)^2 + ((mom_y0 - m2*(beta/alpha)*v2x)/m1)^2) + 1/2*m2*(v2x^2 + ((beta/alpha)*v2x)^2);      <- 1 unknown (v2x)
% expand(E0) gives us 
% -> v2x^2 * [(m2)/2  + (m2^2)/(2*m1) + (beta^2*m2)/(2*alpha^2) + (beta^2*m2^2)/(2*alpha^2*m1) ] + v2x*[-(m2*mom_x0)/m1 - (beta*m2*mom_y0)/(alpha*m1)] + [mom_x0^2/(2*m1)+ mom_y0^2/(2*m1) - E0]


% if (abs(alpha) < abs(beta)) -> v2x = (alpha/beta)*v2y 
E0 = 1/2*m1*(((mom_x0 - m2*(alpha/beta)*v2y)/m1)^2 + ((mom_y0 - m2*v2y)/m1)^2) + 1/2*m2*(((alpha/beta)*v2y)^2 + v2y^2); 
% expand(E0) gives us 
E0 = v2y^2 * [(m2)/2 + (m2^2)/(2*m1)+ (alpha^2*m2^2)/(2*beta^2*m1) + (alpha^2*m2)/(2*beta^2) ] + v2y*[-(m2*mom_y0)/m1 - (alpha*m2*mom_x0)/(beta*m1)] + [mom_x0^2/(2*m1)  + mom_y0^2/(2*m1)]; 


v2y^2[(m2^2)/(2*m1) + (alpha^2*m2^2)/(2*beta^2*m1) + (m2)/2 + (alpha*m2)/(2*beta)] - v2y*[(m2*mom_y0)/m1 - (alpha*m2*mom_x0)/(beta*m1)] + [mom_y0^2/(2*m1) + mom_x0^2/(2*m1) ]





