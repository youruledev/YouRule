package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"useOnlyRawConditions", "rawConditions", "rawBinding", "condition"})
public class RuleEvaluation
{


	private boolean useOnlyRawConditions;
	
	private String rawConditions;
	
	private String rawBinding;

    private Condition condition;

    

    

    


	/**
	 * @param useOnlyRawConditions
	 * @param rawConditions
	 * @param rawBinding
	 * @param condition
	 */
	public RuleEvaluation(boolean useOnlyRawConditions, String rawConditions,
			String rawBinding, Condition condition) {
		this.useOnlyRawConditions = useOnlyRawConditions;
		this.rawConditions = rawConditions;
		this.rawBinding = rawBinding;
		this.condition = condition;
	}

	public RuleEvaluation() {
	}
	
    public boolean getUseOnlyRawConditions ()
    {
        return useOnlyRawConditions;
    }

    public void setUseOnlyRawConditions (boolean useOnlyRawConditions)
    {
        this.useOnlyRawConditions = useOnlyRawConditions;
    }

    public Condition getCondition ()
    {
        return condition;
    }

    public void setCondition (Condition condition)
    {
        this.condition = condition;
    }

    public String getRawBinding ()
    {
        return rawBinding;
    }

    public void setRawBinding (String rawBinding)
    {
        this.rawBinding = rawBinding;
    }

    public String getRawConditions ()
    {
        return rawConditions;
    }

    public void setRawConditions (String rawConditions)
    {
        this.rawConditions = rawConditions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [useOnlyRawConditions = "+useOnlyRawConditions+", condition = "+condition+", rawBinding = "+rawBinding+", rawConditions = "+rawConditions+"]";
    }
}
			
		