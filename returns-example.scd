SerialOSCClient.init;
~arc=SerialOSCEnc.default;


(
~turn0=Returns.new(~arc,0,0.01,5);
~turn1=Returns.new(~arc,1,0.1,7);
~turn2=Returns.new(~arc,2,0.05,4);
~turn3=Returns.new(~arc,3,0.01,4);
)

~turn0.sensitivity_(0.05);
~turn1.setWidth(4);

~turn0.setValue(96);

~turn0.setDeltaFunc({arg enc,value; value.postln});

~turn1.setDeltaFunc({arg enc,value; ~turn0.setWidth(value.linlin(0,1,3,60))});
~turn2.setDeltaFunc({arg enc,value; ~turn1.sensitivity_(value)});

~turn0.setDeltaFunc({arg enc,value; value.postln});

~turn3.setDeltaFunc({arg n,v; ~turn2.setValue(v)});
