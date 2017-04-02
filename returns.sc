Returns
{
	var arc,enc,<>sensitivity,<>width;
	var value,ring,point,index,offset,<oscDef;

	*new
	{
		arg arc,enc,sensitivity,width;

		^super.new.init(arc,enc,sensitivity,width);
	}

	init
	{
		arg arc_,enc_,sensitivity_,width_;

		arc=arc_;enc=enc_;sensitivity=sensitivity_;width=width_;
		value=40;
		ring=0!64;
		point=0!2;
		index=0;
		offset=0;

		oscDef=OSCdef(\turns++enc,
			{
				arg message;
				var encMes=message.at(1),delta=message.at(2);
				if(enc==encMes)
				{
					value=(value+(sensitivity*delta)).clip(40,88);
					this.p(value,width);
					value.postln;
				};
			}, arc.prefix++"/enc/delta";
		);
	}

	setWidth
	{
		arg newWidth;
		width=newWidth;
		this.p(value,width);
	}

	setValue
	{
		arg newValue;
		value=newValue;
		this.p(value,width);
	}

	p
	{
		arg x,w;
		index=x.floor+1;
		offset = (x+1) % index;

		ring=0!64;
		w.do(
			{
				arg i;
				point.put(0,(64+i+index-(w/2).floor)%64+1);
				point.put(1,((-1*cos(((1+i-offset)/w)*pi*2.0) +1)/2*(15.5)).floor);
				ring.put(point.at(0)-1, point.at(1));
			};
		);
		arc.ringmap(enc,ring);
	}
}
