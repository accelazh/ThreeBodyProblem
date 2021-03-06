/*
 * Copyright (c) 2003-2009 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jme.scene.state.jogl;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.jme.renderer.RenderContext;
import com.jme.scene.state.CullState;
import com.jme.scene.state.jogl.records.CullStateRecord;
import com.jme.system.DisplaySystem;

/**
 * <code>JOGLCullState</code>
 *
 * @author Mark Powell
 * @author Tijl Houtbeckers (added flipped culling mode)
 * @author Joshua Slack - reworked for StateRecords.
 * @author Steve Vaughan - JOGL port
 * @version $Id: JOGLCullState.java 4131 2009-03-19 20:15:28Z blaine.dev $
 */
public class JOGLCullState extends CullState {

    private static final long serialVersionUID = 1L;

    /**
     * @see com.jme.scene.state.RenderState#apply()
     */
    public void apply() {
        // ask for the current state record
        RenderContext<?> context = DisplaySystem.getDisplaySystem()
                .getCurrentContext();
        CullStateRecord record = (CullStateRecord) context.getStateRecord(StateType.Cull);
        context.currentStates[StateType.Cull.ordinal()] = this;

        if (isEnabled()) {
            Face useCullMode = getCullFace();

            switch (useCullMode) {
                case Front:
                    setCull(GL.GL_FRONT, record);
                    setCullEnabled(true, record);
                    break;
                case Back:
                    setCull(GL.GL_BACK, record);
                    setCullEnabled(true, record);
                    break;
                case FrontAndBack:
                    setCull(GL.GL_FRONT_AND_BACK, record);
                    setCullEnabled(true, record);
                    break;
                case None:
                    setCullEnabled(false, record);
                    break;
            }
            setGLPolygonWind(getPolygonWind(), record);
        } else {
            setCullEnabled(false, record);
            setGLPolygonWind(PolygonWind.CounterClockWise, record);
        }

        if (!record.isValid())
            record.validate();
    }

    private void setCullEnabled(boolean enable, CullStateRecord record) {
        final GL gl = GLU.getCurrentGL();

        if (!record.isValid() || record.enabled != enable) {
            if (enable)
                gl.glEnable(GL.GL_CULL_FACE);
            else
                gl.glDisable(GL.GL_CULL_FACE);
            record.enabled = enable;
        }
    }

    private void setCull(int face, CullStateRecord record) {
        final GL gl = GLU.getCurrentGL();

        if (!record.isValid() || record.face != face) {
            gl.glCullFace(face);
            record.face = face;
        }
    }

    private void setGLPolygonWind(PolygonWind windOrder, CullStateRecord record) {
        final GL gl = GLU.getCurrentGL();

        if (!record.isValid() || record.windOrder != windOrder) {
            switch (windOrder) {
                case CounterClockWise:
                    gl.glFrontFace(GL.GL_CCW);
                    break;
                case ClockWise:
                    gl.glFrontFace(GL.GL_CW);
                    break;
            }
            record.windOrder = windOrder;
        }
    }

    @Override
    public CullStateRecord createStateRecord() {
        return new CullStateRecord();
    }
}
