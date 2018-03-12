package org.focusrobotique.tools.pid.model;

public class PidSampleLine {

	private InstructionType instructionType;
	private float pidTime;
	private PidType pidType;
	private float normalSpeed;
	private float realSpeed;
	private float normalPosition;
	private float realPosition;
	private float proportionalError;
	private float integralError;
	private float derivativeError;
	private float pExE;
	private float iExE;
	private float dExE;
	private float normalU;
	private float u;

	public InstructionType getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(InstructionType instructionType) {
		this.instructionType = instructionType;
	}

	public float getPidTime() {
		return pidTime;
	}

	public void setPidTime(float pidTime) {
		this.pidTime = pidTime;
	}

	public PidType getPidType() {
		return pidType;
	}

	public void setPidType(PidType pidType) {
		this.pidType = pidType;
	}

	public float getNormalSpeed() {
		return normalSpeed;
	}

	public void setNormalSpeed(float normalSpeed) {
		this.normalSpeed = normalSpeed;
	}

	public float getRealSpeed() {
		return realSpeed;
	}

	public void setRealSpeed(float realSpeed) {
		this.realSpeed = realSpeed;
	}

	public float getNormalPosition() {
		return normalPosition;
	}

	public void setNormalPosition(float normalPosition) {
		this.normalPosition = normalPosition;
	}

	public float getRealPosition() {
		return realPosition;
	}

	public void setRealPosition(float realPosition) {
		this.realPosition = realPosition;
	}

	public float getProportionalError() {
		return proportionalError;
	}

	public void setProportionalError(float proportionalError) {
		this.proportionalError = proportionalError;
	}

	public float getIntegralError() {
		return integralError;
	}

	public void setIntegralError(float integralError) {
		this.integralError = integralError;
	}

	public float getDerivativeError() {
		return derivativeError;
	}

	public void setDerivativeError(float derivativeError) {
		this.derivativeError = derivativeError;
	}

	public float getPExE() {
		return pExE;
	}

	public void setPExE(float pExE) {
		this.pExE = pExE;
	}

	public float getIExE() {
		return iExE;
	}

	public void setIExE(float iExE) {
		this.iExE = iExE;
	}

	public float getDExE() {
		return dExE;
	}

	public void setDExE(float dExE) {
		this.dExE = dExE;
	}

	public float getNormalU() {
		return normalU;
	}

	public void setNormalU(float normalU) {
		this.normalU = normalU;
	}

	public float getU() {
		return u;
	}

	public void setU(float u) {
		this.u = u;
	}

	public PidSampleLine() {

	}

	@Override
	public String toString() {
		return "PidSampleLine [instructionType=" + instructionType + ", pidTime=" + pidTime + ", pidType=" + pidType
				+ ", normalSpeed=" + normalSpeed + ", realSpeed=" + realSpeed + ", normalPosition=" + normalPosition
				+ ", realPosition=" + realPosition + ", proportionalError=" + proportionalError + ", integralError="
				+ integralError + ", derivativeError=" + derivativeError + ", pExE=" + pExE + ", iExE=" + iExE
				+ ", dExE=" + dExE + ", normalU=" + normalU + ", u=" + u + "]";
	}
}
