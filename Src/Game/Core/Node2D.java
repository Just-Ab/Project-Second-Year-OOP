package Game.Core;

import org.joml.*;
import org.joml.Math;

public class Node2D extends Node {

    protected Vector3f position = new Vector3f(0.0f);
    protected Vector3f scale = new Vector3f(1.0f);
    protected float rotation = 0.0f;
    protected boolean visiblity = true;

    private final Vector3f globalPosition = new Vector3f();
    private final Vector3f prevGlobalPosition = new Vector3f();

    private final Vector3f globalScale = new Vector3f(1.0f);
    private final Vector3f prevGlobalScale = new Vector3f(1.0f);

    private float globalRotation = 0.0f;
    private float prevGlobalRotation = 0.0f;

    public Node2D() {}

    public boolean isVisible() {
        return visiblity;
    }

    public void setVisibility(boolean _visiblity) {
        visiblity = _visiblity;
    }

    public Vector3f getGlobalPosition() {
        return new Vector3f(globalPosition);
    }

    public Vector3f getLocalPosition() {
        return new Vector3f(position);
    }

public void setGlobalPosition(Vector3f _newGlobalPosition) {

    Node parentNode = getParent();

    if (parentNode instanceof Node2D parent2D) {
        Vector3f local = 
            new Vector3f(_newGlobalPosition).sub(parent2D.globalPosition).div(parent2D.globalScale);
        setLocalPosition(local);
    } 
    else {
        setLocalPosition(_newGlobalPosition);
    }
}


    public void setLocalPosition(Vector3f _position) {
        position.set(_position);
    }

    protected void setLocalPositionInternal(Vector3f _position) {
        position.set(_position);
    }

    public float getGlobalRotation() {
        return globalRotation;
    }

    public float getLocalRotation() {
        return rotation;
    }

    public void setLocalRotationRad(float _rotation) {
        rotation = _rotation;
    }

    public void setLocalRotationDegrees(float _rotation) {
        rotation = (_rotation * (float) Math.PI) / 180.0f;
    }

    public Vector3f getGlobalScale() {
        return new Vector3f(globalScale);
    }

    public Vector3f getLocalScale() {
        return new Vector3f(scale);
    }

    public void setLocalScale(Vector3f _scale) {
        scale.set(_scale);
    }

    @Override
    protected void updateEngine(float _delta) {

        prevGlobalPosition.set(globalPosition);
        prevGlobalScale.set(globalScale);
        prevGlobalRotation = globalRotation;

        Node parentNode = getParent();
        if (parentNode instanceof Node2D parent2D) {
            globalPosition.set(parent2D.globalPosition).add(new Vector3f(position).mul(parent2D.globalScale));
            globalScale.set(parent2D.globalScale).mul(scale);
            globalRotation = parent2D.globalRotation + rotation;
        } 
        else {
            globalPosition.set(position);
            globalScale.set(scale);
            globalRotation = rotation;
        }

        if (!globalPosition.equals(prevGlobalPosition)) {
            _onGlobalPositionChanged();
        }

        if (!globalScale.equals(prevGlobalScale)) {
            _onGlobalScaleChanged();
        }

        if (globalRotation != prevGlobalRotation) {
            _onGlobalRotationChanged();
        }
    }

    protected void _onGlobalPositionChanged() {}

    protected void _onGlobalScaleChanged() {}

    protected void _onGlobalRotationChanged() {}
}
