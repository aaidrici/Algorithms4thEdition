

f1 = @(N) 2.99*log(N)/log(2);

f2 = @(N) log(( N.^3 + 6*N.^2  + 11*N + 6)./24) / log(2); 

max_val = 1000; 

N = linspace(0,max_val,max_val); 

% experimental results 
x = [10,100,1000,10000]; 
y = [4.5250, 12.0660, 20.8380, 29.8940]; 
x = x(1:3); 
y = y(1:3); 

plot(N, f1(N)); 
hold on; 
plot(N, f2(N));
plot(x,y,'ko');
legend('2.99ln(N)', 'log2((N^3 + 6N^2 +11N + 6)/24)', 'experimental result')
set(gcf, 'color', 'w');
title('Binary Search Tree Height Using Various Methods');

hold off; 
grid on;


