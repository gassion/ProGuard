/*
 * ProGuard -- shrinking, optimization, obfuscation, and preverification
 *             of Java bytecode.
 *
 * Copyright (c) 2002-2007 Eric Lafortune (eric@graphics.cornell.edu)
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package proguard.classfile.attribute;

import proguard.classfile.Clazz;
import proguard.classfile.attribute.visitor.*;

/**
 * This Attribute represents an inner classes attribute.
 *
 * @author Eric Lafortune
 */
public class InnerClassesAttribute extends Attribute
{
    public int                u2classesCount;
    public InnerClassesInfo[] classes;


    /**
     * Creates an uninitialized InnerClassesAttribute.
     */
    public InnerClassesAttribute()
    {
    }


    // Implementations for Attribute.

    public void accept(Clazz clazz, AttributeVisitor attributeVisitor)
    {
        attributeVisitor.visitInnerClassesAttribute(clazz, this);
    }


    /**
     * Applies the given visitor to all inner classes.
     */
    public void innerClassEntriesAccept(Clazz clazz, InnerClassesInfoVisitor innerClassesInfoVisitor)
    {
        for (int index = 0; index < u2classesCount; index++)
        {
            // We don't need double dispatching here, since there is only one
            // type of InnerClassesInfo.
            innerClassesInfoVisitor.visitInnerClassesInfo(clazz, classes[index]);
        }
    }
}
